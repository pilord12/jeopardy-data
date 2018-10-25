package jeopardy.parsers

import jeopardy.TestObjects
import jeopardy.model.JeopardyRounds
import org.scalatest.{Matchers, WordSpec}

class GameParserSpec extends WordSpec with Matchers {
  "GameParser" should {
    "parse basic game information" in {
      val testParser = new GameParser(TestObjects.game12Html)
      val res = testParser.parse.get

      res.id shouldBe "4607"
      res.number shouldBe Some(4607)

      res.firstRound.round shouldBe Some(JeopardyRounds.FIRST)
      res.firstRound.categories.length shouldBe 6

      val testCategory = res.firstRound.categories(0)
      testCategory.title shouldBe "RECENT FICTION"
      testCategory.questions.length shouldBe 5

      val testQuestion = testCategory.questions(0)
      testQuestion.clue shouldBe Some("\"The Princes of Ireland\" is vol. 1 of Edward Rutherfurd's \"Saga\" named for this capital")
      testQuestion.correctAnswer shouldBe Some("Dublin")
      testQuestion.dollarValue shouldBe Some(200)
      testQuestion.isWager shouldBe false

      val dailyDoubleTestQuestion = res.firstRound.categories(4).questions(3)
      dailyDoubleTestQuestion.clue shouldBe Some("Wham's hit \"Wake Me Up Before You Go-Go\" mentions this American dance of the 1930s & '40s")
      dailyDoubleTestQuestion.correctAnswer shouldBe Some("the jitterbug")
      dailyDoubleTestQuestion.dollarValue shouldBe Some(800)
      dailyDoubleTestQuestion.isWager shouldBe true
    }
  }
}
