package jeopardy

import requests.JeopardyGameRequests.getGameInfoHtml

object JeopardyMain {
  def main(args: Array[String]): Unit = {
    println("HELLO WORLD")
    val gameId = -1
    println(getGameInfoHtml(gameId))
  }
}
