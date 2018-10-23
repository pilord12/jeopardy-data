package jeopardy.parsers

import jeopardy.model._
import jeopardy.utils.Utils.{ intOrNone, parseGameNumberFromTitleString }
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser

import ParsingSelectors._

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
          categories = List.empty[JeopardyCategory]
        ),
        thirdRound = JeopardyRound(
          round = None,
          categories = List.empty[JeopardyCategory]
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

    val categoriesWithNames = for {
      categoryNames <- categoryNamesOpt.toVector
      (categoryName, columnIndex) <- categoryNames.toVector.zipWithIndex
      questions = (1 to 5).map(parseClue(columnIndex + 1, _)) // the HTML is 1-indexed
    } yield {
      JeopardyCategory(
        title = categoryName.toUpperCase,
        questions = questions.toList
      )
    }

    JeopardyRound(
      round = Some(CategoryRounds.FIRST),
      categories = categoriesWithNames.toList
    )
  }

  /**
    * Parses information about a particular clue, given a column and a row value indicating the clue's position on the board
    * @param col the clue's column
    * @param row the clue's row
    * @return a JeopardyClue containing information about the clue at the specified location
    */
  private def parseClue(col: Int, row: Int): JeopardyQuestion = {
    val clueText = doc >?> text(firstRoundClueSelector(col, row))

    JeopardyQuestion(
      clue = clueText,
      correctAnswer = None,
      dollarValue = None,
      isWager = false
    )
  }
}
