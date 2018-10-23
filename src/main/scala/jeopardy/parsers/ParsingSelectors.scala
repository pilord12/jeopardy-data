package jeopardy.parsers

/**
  * Constants useful for selecting and parsing HTML
  */
object ParsingSelectors {
  val TITLE_SELECTOR: String = "div#game_title"
  val FIRST_ROUND_SELECTOR: String = "div#jeopardy_round"

  val CATEGORY_SELECTOR: String = "td.category td.category_name"
  val FIRST_ROUND_CATEGORIES_SELECTOR: String = s"$FIRST_ROUND_SELECTOR $CATEGORY_SELECTOR"

  /**
    * Builds a selector for a clue from a column and row
    * @param col the column in the clue table
    * @param row the row in the clue table
    * @return a string for selecting the clue text of that column/row
    */
  def firstRoundClueSelector(col: Int, row: Int): String = s"td#clue_J_${col}_$row"
}
