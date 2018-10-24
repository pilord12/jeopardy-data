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

  val CATEGORIES_PER_ROUND: Int = 6

  val CLUE_ANSWER_RE: Regex = """.*<em class="correct_response">(?:<[a-zA-Z]+>)?([^<]*)(?:</[a-zA-Z]+>)?</em>.*""".r

  /**
    * Builds a selector for a clue from a column and row
    * @param col the column in the clue table
    * @param row the row in the clue table
    * @return a string for selecting the clue text of that column/row
    */
  def firstRoundClueSelector(col: Int, row: Int): String = s"td#clue_J_${col}_$row"
}
