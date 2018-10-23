package jeopardy.parsers

import jeopardy.model._
import jeopardy.utils.Utils.{ intOrNone, parseGameNumberFromTitleString }
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.browser.JsoupBrowser

class GameParser(html: String) {
  private val browser = JsoupBrowser()
  private val doc = browser.parseString(html)

  def parse(): Option[JeopardyGame] = {
    val titleOpt = doc >?> text("div#game_title")

    for {
      title <- titleOpt
      id <- parseGameNumberFromTitleString(title)
    } yield {
      JeopardyGame(
        id = id,
        number = intOrNone(id),
        firstRound = JeopardyRound(
          categories = List.empty[JeopardyCategory]
        ),
        secondRound = JeopardyRound(
          categories = List.empty[JeopardyCategory]
        ),
        thirdRound = JeopardyRound(
          categories = List.empty[JeopardyCategory]
        )
      )
    }
  }
}
