package jeopardy.parsers

import jeopardy.TestObjects
import jeopardy.model.JeopardyRounds
import org.scalatest.{Matchers, WordSpec}

class GameParserSpec extends WordSpec with Matchers {
  "GameParser" should {
    "parse a full valid Jeopardy game" in {
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

      res.secondRound.round shouldBe Some(JeopardyRounds.SECOND)
      res.firstRound.categories.length shouldBe 6

      val testDoubleCategory = res.secondRound.categories(3)
      testDoubleCategory.title shouldBe "GREAT MOMENTS IN HISTORY"
      testDoubleCategory.questions.length shouldBe 5

      val testDoubleQuestion = testDoubleCategory.questions(4)
      testDoubleQuestion.clue shouldBe Some("Inspired by a glass of beer, Donald Glaser made this device in 1952 to track cosmic rays")
      testDoubleQuestion.correctAnswer shouldBe Some("the bubble chamber")
      testDoubleQuestion.dollarValue shouldBe Some(2000)
      testDoubleQuestion.isWager shouldBe false

      val dailyDoubleTestDoubleQuestion = res.secondRound.categories(2).questions(2)
      dailyDoubleTestDoubleQuestion.clue shouldBe
        Some("After being a 16th C. version of \"The Terminator\", the title guy buys the farm too at the end of this, Will's 1st tragedy")
      dailyDoubleTestDoubleQuestion.correctAnswer shouldBe Some("Titus Andronicus")
      dailyDoubleTestDoubleQuestion.dollarValue shouldBe Some(1200)
      dailyDoubleTestDoubleQuestion.isWager shouldBe true

      res.thirdRound.round shouldBe Some(JeopardyRounds.FINAL)
      res.thirdRound.categories.length shouldBe 1

      val testFinalCategory = res.thirdRound.categories(0)
      testFinalCategory.title shouldBe "FIRST LADIES"
      testFinalCategory.questions.length shouldBe 1

      val testFinalQuestion = testFinalCategory.questions(0)
      testFinalQuestion.clue shouldBe
        Some("She survived the President by 39 years & was married to an archaeology professor at the time of her own death in 1947")
      testFinalQuestion.correctAnswer shouldBe Some("Frances Folsom Cleveland")
      testFinalQuestion.dollarValue shouldBe None
      testFinalQuestion.isWager shouldBe true

      res shouldBe TestObjects.expectedGame12Object
    }
  }
}
