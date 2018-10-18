package jeopardy.model

/**
  *
  * @param clue string of the question's clue text
  * @param correctAnswer correct answer to the clue
  * @param dollarValue optional dollar amount which the question is worth; empty for Final Jeopardy questions
  * @param isWager whether the clue requires a wager on the contestant's part; true for Daily Double and Final Jeopardy questions
  */
case class JeopardyQuestion(
  clue: String, // TODO add support for multimedia clues
  correctAnswer: String,
  dollarValue: Option[Int],
  isWager: Boolean = false
)
