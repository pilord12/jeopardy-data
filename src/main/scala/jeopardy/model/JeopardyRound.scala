package jeopardy.model


/**
  * Possible rounds in a Jeopardy game could be in
  */
case object JeopardyRounds {
  val FIRST = 1
  val SECOND = 2
  val FINAL = 3

  private val DOLLAR_AMOUNT_BASE = 200

  /**
    * Converts a row and round number to what the dollar amount for that clue should be
    * @param roundNum number of the round
    * @param rowNum number of the row (1-indexed)
    * @return a dollar value if the row and round combo is valid; None otherwise
    */
  def rowToDollarAmount(roundNum: Int, rowNum: Int): Option[Int] = {
    if((roundNum == 1 || roundNum == 2) && (rowNum >= 1 && rowNum <= 5)) {
      Some(roundNum * rowNum * DOLLAR_AMOUNT_BASE)
    } else {
      None
    }
  }

  /**
    * Converts a round number to a human-readable string
    * @param n number representing a Jeopardy round
    * @return the name of the round
    */
  def roundNumToStringOpt(n: Int): Option[String] = {
    n match {
      case 1 => Some("Jeopardy")
      case 2 => Some("Double Jeopardy")
      case 3 => Some("Final Jeopardy")
      case _ => None
    }
  }
}

/**
  * Representation of one Jeopardy game round
  * @param round the string representing the round of game this is
  * @param categories list of categories in the round
  */
case class JeopardyRound(
  round: Option[Int],
  categories: Vector[JeopardyCategory]
) {

}

object JeopardyRound {

}
