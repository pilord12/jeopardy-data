package jeopardy

import requests.JeopardyGameRequests.getGameInfoHtml

object Jeopardy {
  def main(args: Array[String]): Unit = {
    println("HELLO WORLD")
    println(getGameInfoHtml(gameId = 400))
  }
}
