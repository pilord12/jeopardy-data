package jeopardy.model

/**
  * Class to represent one game of Jeopardy
  * @param id string ID of the episode
  * @param number episode number; probably the same as the ID
  * @param firstRound "Jeopardy" round of the game
  * @param secondRound "Double Jeopardy" round of the game
  * @param thirdRound "Final Jeopardy" round of the game
  */
case class JeopardyGame(
  id: String,
  number: Int,
  firstRound: JeopardyRound,
  secondRound: JeopardyRound,
  thirdRound: JeopardyRound
)

object JeopardyGame {

}
