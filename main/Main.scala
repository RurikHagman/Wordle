package wdl
  object Main:
    val defaultUrl = "https://www.w3.org/services/html2txt?url=https%3A%2F%2Fwww.telegraph.co.uk%2F"
    val defaultN = 10

    var urlText = Text.fromURL(defaultUrl)

    var text: Text = Text.fromFile(introprog.IO.currentDir() + """\""" + "words.txt")

    val word: String = text.randomElem((text.wordsOfLength(5)).toVector)
    
    val game = wordleGame(word)

    def main(args: Array[String]): Unit =

      drawGame()    
      drawKeys()
      game.play()
      
      

    