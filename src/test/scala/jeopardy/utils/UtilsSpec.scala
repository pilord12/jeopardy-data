package jeopardy.utils

import java.time.ZoneId

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

    "parse the game ID from the title string" in {
      parseGameNumberFromTitleString("Show #4607 - Tuesday, September 21, 2004") shouldBe Some("4607")
      parseGameNumberFromTitleString("Show Tuesday, September 21, 2004") shouldBe None
    }

    "optionally parse integers from strings" in {
      intOrNone("12") shouldBe Some(12)
      intOrNone("-1") shouldBe Some(-1)
      intOrNone("asasdoifj") shouldBe None
      intOrNone("123kjl") shouldBe None
      intOrNone("") shouldBe None
    }

    "sanitize strings to remove unneeded escape characters" in {
      sanitizeString("theSameString") shouldBe "theSameString"
      sanitizeString("different\\String") shouldBe "differentString"
      sanitizeString("\\different\\String\\") shouldBe "differentString"
      sanitizeString("different\\\\Strin\\g") shouldBe "differentString"
    }

    "parse dates of the expected format" in {
      val date = dateOrNone("Tuesday, September 21, 2004").get.atZone(ZoneId.of("UTC")).toLocalDate

      date.getMonthValue shouldBe 9
      date.getDayOfMonth shouldBe 21
      date.getYear shouldBe 2004
    }

    "gracefully fail when given an invalid date" in {
      val instOpt = dateOrNone("definitely not a valid date string")
      instOpt should not be defined
    }

    "parse the date from the title string" in {
      val titleString = "Show #4607 - Tuesday, September 21, 2004"
      val invalidTitleString = "Show Tuesday, September 21, 2004"

      parseDateFromTitleString(titleString) shouldBe dateOrNone("Tuesday, September 21, 2004")
      parseDateFromTitleString(invalidTitleString) shouldBe None
    }
  }
}
