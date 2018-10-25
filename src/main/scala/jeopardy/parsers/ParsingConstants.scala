package jeopardy.parsers

import scala.util.matching.Regex

/**
  * Constants useful for selecting and parsing HTML
  */
object ParsingConstants {
  val TITLE_SELECTOR: String = "div#game_title"
  val FIRST_ROUND_SELECTOR: String = "div#jeopardy_round"
  val CLUE_SELECTOR: String = "td.clue"

  val CATEGORY_SELECTOR: String = "td.category td.category_name"
  val FIRST_ROUND_CATEGORIES_SELECTOR: String = s"$FIRST_ROUND_SELECTOR $CATEGORY_SELECTOR"
  val FIRST_ROUND_CLUES_SELECTOR: String = s"$FIRST_ROUND_SELECTOR $CLUE_SELECTOR"

  val CLUE_TEXT_SELECTOR: String = "td.clue_text"
  val CLUE_ANSWER_SELECTOR: String = "tr > td > div"
  val CLUE_ANSWER_ATTRIBUTE: String = "onmouseover"
  val DAILY_DOUBLE_SELECTOR: String = "td.clue_value_daily_double"

  val CATEGORIES_PER_ROUND: Int = 6

  val CLUE_ANSWER_RE: Regex = """.*<em class="correct_response">(?:<[a-zA-Z]+>)?([^<]*)(?:</[a-zA-Z]+>)?</em>.*""".r
  val DOLLAR_AMOUNT_RE: Regex = """\$(\d+)""".r
}
