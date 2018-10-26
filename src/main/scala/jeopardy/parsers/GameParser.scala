package jeopardy.parsers

import jeopardy.model._
import jeopardy.utils.Utils.{intOrNone, parseGameNumberFromTitleString}
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model.Element
import ParsingConstants._
import jeopardy.utils.Utils

/**
  * A parser which extracts information about a game from given HTML
  * @param html
  */
class GameParser(html: String) {
  private val browser = JsoupBrowser()
  private val doc = browser.parseString(html)

  /**
    * Parses the HTML to get all information about a Jeopardy game
    * @return an optional JeopardyGame representing the information in the HTML file
    */
  def parse: Option[JeopardyGame] = {
    val titleOpt = doc >?> text(TITLE_SELECTOR)

    for {
      title <- titleOpt
      id <- parseGameNumberFromTitleString(title)
    } yield {
      JeopardyGame(
        id = id,
        number = intOrNone(id),
        firstRound = parseRound(JeopardyRounds.FIRST),
        secondRound = parseRound(JeopardyRounds.SECOND),
        thirdRound = parseRound(JeopardyRounds.FINAL)
      )
    }
  }

  /**
    * Parses a single round of Jeopardy information
    * @return a JeopardyRound representing the round parsed
    */
  private def parseRound(roundNum: Int): JeopardyRound = {
    val selectors = selectorsForRound(roundNum)

    val categoryNamesOpt = selectors.flatMap(s => doc >?> texts(s.categoriesSelector))

    // Since there is only one category and clue in the final round, just use the whole round HTML since the answer is on the category, not the clue
    val clueElements = if (roundNum == JeopardyRounds.FINAL) {
      selectors.flatMap(s => doc >?> elements(s.roundSelector))
    } else {
      selectors.flatMap(s => doc >?> elements(s.cluesSelector))
    }
    val groupedClueElementsOpt = clueElements.map(_.zipWithIndex.groupBy { case (_, i) =>
      i % CATEGORIES_PER_ROUND // Since we need to reverse rows/columns, group by their position in the row
    }.toVector.sortBy { case (colPos, _) => // Sort by their column number
      colPos
    }.map { case (_, column) =>
      column.zipWithIndex.map { case ((clueHtml, _), rowNum) => // Grab the clue HTML and not the index
        (clueHtml, rowNum + 1) // Add 1 so it's 1-indexed and not 0-indexed
      }
    }) // We are left with a list of lists of clue HTML and row numbers, in order from left-to-right by column

    val categories = for {
      categoryNames <- categoryNamesOpt.toVector
      groupedClueElements <- groupedClueElementsOpt.toVector
      zippedNamesAndClues = categoryNames.zip(groupedClueElements)
      (categoryName, categoryCluesHtml) <- zippedNamesAndClues
      categoryQuestions = categoryCluesHtml.map { case (htmlElement, row) =>
        parseClue(htmlElement, row, roundNum)
      }.toVector
    } yield {
      JeopardyCategory(
        title = categoryName.toUpperCase,
        questions = categoryQuestions
      )
    }

    JeopardyRound(
      round = Some(roundNum),
      categories = categories
    )
  }

  /**
    * Parses information about a particular clue, given some HTML from the board
    * @param htmlElement the HTML for the full table cell of the clue
    * @param rowNum the number of the row, for purposes of calculating dollar amount
    * @return a JeopardyClue containing information about the clue outlined in the HTML
    */
  private def parseClue(htmlElement: Element, rowNum: Int, roundNum: Int): JeopardyQuestion = {
    val clueTextOpt = htmlElement >?> text(CLUE_TEXT_SELECTOR)
    val answerElementOpt = htmlElement >?> element(CLUE_ANSWER_SELECTOR)

    val answerOpt = for {
      answerElement <- answerElementOpt
      if answerElement.hasAttr(CLUE_ANSWER_ATTRIBUTE)
      answerElementMouseover = answerElement.attr(CLUE_ANSWER_ATTRIBUTE)
      answerRegexMatch <- Utils.sanitizeString(answerElementMouseover) match {
        case CLUE_ANSWER_RE(answer) => Some(answer)
        case _ => None
      }
    } yield {
      answerRegexMatch
    }

    val value = JeopardyRounds.rowToDollarAmount(roundNum = roundNum, rowNum = rowNum)

    val isWager = (htmlElement >?> element(DAILY_DOUBLE_SELECTOR)).isDefined || roundNum == JeopardyRounds.FINAL // The final round is always a wager

    JeopardyQuestion(
      clue = clueTextOpt,
      correctAnswer = answerOpt,
      dollarValue = value,
      isWager = isWager
    )
  }
}
