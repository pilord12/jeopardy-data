package jeopardy.parsers

import jeopardy.TestObjects
import org.scalatest.{ Matchers, WordSpec }

class GameParserSpec extends WordSpec with Matchers {
  "GameParser" should {
    "parse basic game information" in {
      val testParser = new GameParser(TestObjects.game12Html)
      val res = testParser.parse().get

      res.id shouldBe "4607"
      res.number shouldBe Some(4607)
    }
  }
}
