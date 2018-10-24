package jeopardy.model

/**
  * Representation of a single category of Jeopardy questions
  * @param title the title of the category
  * @param questions list of} questions and related information in the category
  */
case class JeopardyCategory(
  title: String,
  questions: Vector[JeopardyQuestion]
)

object JeopardyCategory {

}
