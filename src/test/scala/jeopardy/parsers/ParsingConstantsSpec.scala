package jeopardy.parsers

import org.scalatest.{ Matchers, WordSpec }

class ParsingConstantsSpec extends WordSpec with Matchers {
  "ParsingConstants" should {
    "get the correct selectors given a round number" in {
      ParsingConstants.selectorsForRound(1) shouldBe Some(ParsingConstants.FIRST_ROUND_SELECTORS)
      ParsingConstants.selectorsForRound(2) shouldBe Some(ParsingConstants.SECOND_ROUND_SELECTORS)
      ParsingConstants.selectorsForRound(0) shouldBe None
      ParsingConstants.selectorsForRound(12) shouldBe None
    }
  }
}
