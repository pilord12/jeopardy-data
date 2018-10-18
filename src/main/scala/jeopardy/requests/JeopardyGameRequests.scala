package jeopardy.requests

import jeopardy.utils.Utils._
import scalaj.http.{Http, HttpResponse}

/**
  * Utility methods for requesting game information
  */
object JeopardyGameRequests {

  /**
    * Gets the string HTML of a Jeopardy game by ID.  Checks to make sure the response is successful and the game ID requested was valid.
    * @param gameId the numeric ID of a Jeopardy game
    * @return the HTML string in the case of a success, or None in the case of a failure
    */
  def getGameInfoHtml(gameId: Int): Option[String] = {
    val url: String = gameUrl(gameId)
    val response: HttpResponse[String] = Http(url).asString

    if (response.isSuccess && checkValidGameResponseHtml(response.body)) Some(response.body) else None
  }
}
