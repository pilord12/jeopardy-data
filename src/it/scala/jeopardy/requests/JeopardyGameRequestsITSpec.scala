package jeopardy.requests

import jeopardy.requests.JeopardyGameRequests._
import org.scalatest.{Matchers, WordSpec}

class JeopardyGameRequestsITSpec extends WordSpec with Matchers {
  "JeopardyGameRequests" should {
    "get HTML from a valid game URL" in {
      getGameInfoHtml(12) shouldBe defined
    }

    "get nothing from an invalid game URL" in {
      getGameInfoHtml(-1) shouldBe None
      getGameInfoHtml(Int.MaxValue) shouldBe None
    }
  }
}
