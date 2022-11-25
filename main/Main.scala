package wdl
  object Main:
    val defaultUrl = "https://www.w3.org/services/html2txt?url=https%3A%2F%2Fwww.telegraph.co.uk%2F"
    val defaultN = 10
    var filepath: String = introprog.IO.currentDir() + """\"""

    var urlText = Text.fromURL(defaultUrl)
    val dictionary = Text.fromFile(filepath + "dictionary.txt")
    var text: Text = Text.fromFile(filepath + "words.txt")

    val word: String = text.randomElem((text.wordsOfLength(5)).toVector)
    val game = wordleGame(word)

    def main(args: Array[String]): Unit =
      if System.getProperty("os.name").contains("Mac") then filepath = (introprog.IO.currentDir() + """/""")
      // test dictionary content: text.words.foreach(s => if !dictionary.words.contains(s) then println(s))
      game.play()
      
      

    