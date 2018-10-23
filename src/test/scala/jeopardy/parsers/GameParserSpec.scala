package jeopardy.parsers

import jeopardy.TestObjects
import jeopardy.model.CategoryRounds
import org.scalatest.{Matchers, WordSpec}

class GameParserSpec extends WordSpec with Matchers {
  "GameParser" should {
    "parse basic game information" in {
      val testParser = new GameParser(TestObjects.game12Html)
      val res = testParser.parse.get

      res.id shouldBe "4607"
      res.number shouldBe Some(4607)

      res.firstRound.round shouldBe Some(CategoryRounds.FIRST)
      res.firstRound.categories.length shouldBe 6

      val testCategory = res.firstRound.categories.find(_.title == "RECENT FICTION").get
      testCategory.title shouldBe "RECENT FICTION"
      testCategory.questions.length shouldBe 5

      val testQuestion = testCategory.questions(0)
      testQuestion.clue shouldBe Some("\"The Princes of Ireland\" is vol. 1 of Edward Rutherfurd's \"Saga\" named for this capital")

    }
  }
}
