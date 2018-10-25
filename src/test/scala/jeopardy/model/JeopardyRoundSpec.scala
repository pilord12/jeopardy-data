package jeopardy.model

import org.scalatest.{ Matchers, WordSpec }

class JeopardyRoundSpec extends WordSpec with Matchers {
  "JeopardyRound" should {
    "convert valid round numbers to strings" in {
      JeopardyRounds.roundNumToStringOpt(1) shouldBe Some("Jeopardy")
      JeopardyRounds.roundNumToStringOpt(2) shouldBe Some("Double Jeopardy")
      JeopardyRounds.roundNumToStringOpt(3) shouldBe Some("Final Jeopardy")
      JeopardyRounds.roundNumToStringOpt(-1) shouldBe None
      JeopardyRounds.roundNumToStringOpt(0) shouldBe None
      JeopardyRounds.roundNumToStringOpt(12) shouldBe None
    }

    "calculate dollar amounts given valid rows and rounds" in {
      JeopardyRounds.rowToDollarAmount(roundNum = 1, rowNum = 2) shouldBe Some(400)
      JeopardyRounds.rowToDollarAmount(roundNum = 2, rowNum = 1) shouldBe Some(400)
      JeopardyRounds.rowToDollarAmount(roundNum = 2, rowNum = 5) shouldBe Some(2000)
      JeopardyRounds.rowToDollarAmount(roundNum = 3, rowNum = 2) shouldBe None
      JeopardyRounds.rowToDollarAmount(roundNum = 1, rowNum = 22) shouldBe None
      JeopardyRounds.rowToDollarAmount(roundNum = 1, rowNum = -1) shouldBe None
      JeopardyRounds.rowToDollarAmount(roundNum = 4, rowNum = 8) shouldBe None
    }
  }
}
