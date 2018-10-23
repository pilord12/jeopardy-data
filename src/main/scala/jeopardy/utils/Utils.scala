package jeopardy.utils

import scala.util.matching.Regex

/**
  * Basic utility methods and constants
  */
object Utils {
  private val BASE_URL = "http://www.j-archive.com"
  private val BASE_GAME_URL = BASE_URL + "/showgame.php?game_id="
  private val VALID_GAME_RE: Regex = """.*Show #(\d+) - .*""".r

  /**
    * Converts a numerical game ID to a URL for requesting info for that game
    * @param num the integer representing the number of the game
    * @return the URL to GET game HTML information from
    */
  def gameUrl(num: Int): String = BASE_GAME_URL + num.toString

  /**
    * Checks an HTML string to see if constitutes HTML for a valid Jeopardy game
    * @param htmlString the HTML to check
    * @return true if valid, false if not
    */
  def checkValidGameResponseHtml(htmlString: String): Boolean = {
    VALID_GAME_RE.findFirstMatchIn(htmlString).isDefined
  }

  /**
    * Gets the game number (ID) from the title string
    * @param title the string of the title info of the show, as pulled from the HTML
    * @return the string of the show's number, if it is found
    */
  def parseGameNumberFromTitleString(title: String): Option[String] = {
    title match {
      case VALID_GAME_RE(showNum) => Some(showNum)
      case _ => None
    }
  }

  /**
    * Attempts to parse an integer from a string
    * @param str the string to parse
    * @return the int parsed, or None if no int was parsed
    */
  def intOrNone(str: String): Option[Int] = {
    try {
      Some(str.toInt)
    } catch {
      case e: NumberFormatException => None
    }
  }
}
