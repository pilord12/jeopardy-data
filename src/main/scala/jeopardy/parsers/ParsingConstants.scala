package jeopardy.parsers

import scala.util.matching.Regex

/**
  * Constants useful for selecting and parsing HTML
  */
object ParsingConstants {
  val TITLE_SELECTOR: String = "div#game_title"
  val CLUE_SELECTOR: String = "td.clue"
  val CATEGORY_SELECTOR: String = "td.category td.category_name"

  val FIRST_ROUND_SELECTOR: String = "div#jeopardy_round"
  val FIRST_ROUND_CATEGORIES_SELECTOR: String = s"$FIRST_ROUND_SELECTOR $CATEGORY_SELECTOR"
  val FIRST_ROUND_CLUES_SELECTOR: String = s"$FIRST_ROUND_SELECTOR $CLUE_SELECTOR"

  val SECOND_ROUND_SELECTOR: String = "div#double_jeopardy_round"
  val SECOND_ROUND_CATEGORIES_SELECTOR: String = s"$SECOND_ROUND_SELECTOR $CATEGORY_SELECTOR"
  val SECOND_ROUND_CLUES_SELECTOR: String = s"$SECOND_ROUND_SELECTOR $CLUE_SELECTOR"

  val FIRST_ROUND_SELECTORS = RoundSelectors(
    roundSelector = FIRST_ROUND_SELECTOR,
    categoriesSelector = FIRST_ROUND_CATEGORIES_SELECTOR,
    cluesSelector = FIRST_ROUND_CLUES_SELECTOR
  )

  val SECOND_ROUND_SELECTORS = RoundSelectors(
    roundSelector = SECOND_ROUND_SELECTOR,
    categoriesSelector = SECOND_ROUND_CATEGORIES_SELECTOR,
    cluesSelector = SECOND_ROUND_CLUES_SELECTOR
  )

  /**
    * Determines what selectors need to be used for the given round
    * @param roundNum the round to check for
    * @return appropriate CSS selectors for that round
    */
  def selectorsForRound(roundNum: Int): Option[RoundSelectors] = {
    roundNum match {
      case 1 => Some(FIRST_ROUND_SELECTORS)
      case 2 => Some(SECOND_ROUND_SELECTORS)
      case _ => None
    }
  }

  val CLUE_TEXT_SELECTOR: String = "td.clue_text"
  val CLUE_ANSWER_SELECTOR: String = "tr > td > div"
  val CLUE_ANSWER_ATTRIBUTE: String = "onmouseover"
  val DAILY_DOUBLE_SELECTOR: String = "td.clue_value_daily_double"

  val CATEGORIES_PER_ROUND: Int = 6

  val CLUE_ANSWER_RE: Regex = """.*<em class="correct_response">(?:<[a-zA-Z]+>)?([^<]*)(?:</[a-zA-Z]+>)?</em>.*""".r
  val DOLLAR_AMOUNT_RE: Regex = """\$(\d+)""".r
}

case class RoundSelectors(
  roundSelector: String,
  categoriesSelector: String,
  cluesSelector: String
)
