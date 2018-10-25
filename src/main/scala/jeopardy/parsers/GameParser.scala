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
        firstRound = parseRound,
        secondRound = JeopardyRound(
          round = None,
          categories = Vector.empty[JeopardyCategory]
        ),
        thirdRound = JeopardyRound(
          round = None,
          categories = Vector.empty[JeopardyCategory]
        )
      )
    }
  }

  /**
    * Parses a single round of Jeopardy information
    * @return a JeopardyRound representing the round parsed
    */
  private def parseRound: JeopardyRound = {
    val categoryNamesOpt = doc >?> texts(FIRST_ROUND_CATEGORIES_SELECTOR)

    val clueElements = doc >?> elements(FIRST_ROUND_CLUES_SELECTOR)
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
        parseClue(htmlElement, row)
      }.toVector
    } yield {
      JeopardyCategory(
        title = categoryName.toUpperCase,
        questions = categoryQuestions
      )
    }

    JeopardyRound(
      round = Some(JeopardyRounds.FIRST),
      categories = categories
    )
  }

  /**
    * Parses information about a particular clue, given some HTML from the board
    * @param htmlElement the HTML for the full table cell of the clue
    * @param rowNum the number of the row, for purposes of calculating dollar amount
    * @return a JeopardyClue containing information about the clue outlined in the HTML
    */
  private def parseClue(htmlElement: Element, rowNum: Int): JeopardyQuestion = {
    val clueTextOpt = htmlElement >?> text(CLUE_TEXT_SELECTOR)
    val answerElementOpt = htmlElement >?> element(CLUE_ANSWER_SELECTOR)

    val answerOpt = for {
      answerElement <- answerElementOpt
      if answerElement.hasAttr(CLUE_ANSWER_ATTRIBUTE)
      answerElementMouseover = answerElement.attr(CLUE_ANSWER_ATTRIBUTE)
      answerRegexMatch <- answerElementMouseover match {
        case CLUE_ANSWER_RE(answer) => Some(Utils.sanitizeString(answer))
        case _ => None
      }
    } yield {
      answerRegexMatch
    }

    val value = JeopardyRounds.rowToDollarAmount(roundNum = 1, rowNum = rowNum)

    val isWager = (htmlElement >?> element(DAILY_DOUBLE_SELECTOR)).isDefined

    JeopardyQuestion(
      clue = clueTextOpt,
      correctAnswer = answerOpt,
      dollarValue = value,
      isWager = isWager
    )
  }
}
