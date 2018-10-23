package jeopardy.model


/**
  * Possible rounds a category could be in
  */
case object CategoryRounds {
  val FIRST = "FIRST"
  val SECOND = "SECOND"
  val FINAL = "FINAL"
}

/**
  * Representation of one Jeopardy game round
  * @param round the string representing the round of game this is
  * @param categories list of categories in the round
  */
case class JeopardyRound(
  round: Option[String],
  categories: List[JeopardyCategory]
) {

}

object JeopardyRound {

}
