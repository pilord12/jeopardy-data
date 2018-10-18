package jeopardy.utils

import jeopardy.TestObjects
import jeopardy.utils.Utils._
import org.scalatest.{Matchers, WordSpec}

class UtilsSpec extends WordSpec with Matchers {
  "Utils" should {
    "form a game URL" in {
      gameUrl(12) shouldEqual "http://www.j-archive.com/showgame.php?game_id=12"
    }

    "determine what game HTML is valid" in {
      checkValidGameResponseHtml(TestObjects.game12Html) shouldBe true
      checkValidGameResponseHtml(TestObjects.invalidGameHtml) shouldBe false
      checkValidGameResponseHtml("This should really be invalid") shouldBe false
    }
  }
}