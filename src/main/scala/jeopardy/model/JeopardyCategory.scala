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
  * Representation of a single category of Jeopardy questions
  * @param title the title of the category
  * @param round roung of the game which the category is in
  * @param questions list of questions and related information in the category
  */
case class JeopardyCategory(
  title: String,
  round: String,
  questions: List[JeopardyQuestion]
)

object JeopardyCategory {

}
