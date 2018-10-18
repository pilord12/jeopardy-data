package jeopardy

import io.Source

object TestObjects {
  private val TEST_FILES_BASE_LOC = "src/test/resources"
  private val TEST_GAME_FILES_LOC = TEST_FILES_BASE_LOC + "/games"

  /**
    * Gets the full project path of a test game file
    * @param filename simple filename to search for
    * @return project path string leading to the filename
    */
  private def gameFileFullPath(filename: String): String = TEST_GAME_FILES_LOC + s"/$filename"

  lazy val game12Html: String = Source.fromFile(gameFileFullPath("game12.html")).mkString
  lazy val invalidGameHtml: String = Source.fromFile(gameFileFullPath("invalid_game.html")).mkString
}
