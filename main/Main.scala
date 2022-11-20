package wdl
  object Main:
    val defaultUrl = "https://www.w3.org/services/html2txt?url=https%3A%2F%2Fwww.telegraph.co.uk%2F"
    val defaultN = 10

    

    var text: Text = Text.fromURL(defaultUrl)

    val word: String = text.randomElem((text.wordsOfLength(5).filter(w => text.wordFreq(w) != 1) - "https").toVector)
    
    val game = wordleGame(word)

    def main(args: Array[String]): Unit =
      
      println(word)
    
      
      

    