package wdl
import scala.collection.mutable.StringBuilder

class wordleGame(word: String):
   
    var guesses: Int = 6
    var gameOver = false


    val wordSeq = word.toUpperCase().toArray                    //en array med chars av det rätta ordet
    var correctApply = Array(' ', ' ', ' ', ' ', ' ')           //används för att kolla om en bokstav har gissats på rätt plats

    val wordMatrix: Vector[Array[Char]] = Vector.fill(6)(Array(' ', ' ', ' ', ' ', ' '))       //matrisen med alla gissade ord
    
    var charPos: (Int, Int) = (0, 0)                                                //positionen i matrisen där bokstaven ska vara

    def gameWon(): Unit = 
        gameOver = true
        println("GAME OVER")

    def outofGuess = 
        gameOver = true
        println("GAME OVER! The word was: " + word) 
    
    def updateKeyboard(key: Char, ecolor: java.awt.Color): Unit =                   //uppdaterar "tangentbordet" i spelet 
        if row1.contains(key)      then drawLetterBlock((12, row1.indexOf(key)), key, ecolor, 21)
        else if row2.contains(key) then drawLetterBlock((13, row2.indexOf(key)), key, ecolor, 21)
        else if row3.contains(key) then drawLetterBlock((14, row3.indexOf(key) + 1), key, ecolor, 21)

    def handleKey(): Unit = 

        var key: String = window.lastKey
        var keyChar: Char = window.lastKey.capitalize.apply(0)

        if key.length == 1 && alfabet.contains(keyChar) then            //kollar om tangenten är en bokstavstangent och ritar ut på rätt plats
            if charPos(1) <= 4 then
                wordMatrix(charPos(0))(charPos(1)) = keyChar
                drawLetterBlock((charPos(0) + 1, charPos(1) + 1) , keyChar, State.Idle, 0)
                charPos = (charPos(0), charPos(1) + 1)
        
        else if key == "Backspace" then                                 //suddar på rätt plats
            if charPos(1) >= 1 then
                wordMatrix(charPos(0))(charPos(1) - 1) = ' '
                charPos = (charPos(0), charPos(1) - 1)
                drawLetterBlock((charPos(0) + 1 , charPos(1) + 1) , ' ', State.Idle, 0)
        
        else if key == "Enter" then                                    //om kraven för en tillåten gissning uppfylls: ritar ut gissningen och uppdaterar "tangentbordet"
            if !wordMatrix(charPos(0)).contains(' ') then
                
                val wordGuess: Array[Char] = wordMatrix(charPos(0))
                val wordStringBuilder = StringBuilder()
                wordGuess.foreach(c => wordStringBuilder += c.toLower)
                val wordString: String = wordStringBuilder.toString

                if Main.dictionary.source.contains(wordString) then     //kollar om gissningen är ett ord
                    var index = 0

                    wordGuess.foreach(c =>                              //för varje bokstav i gissningen
                        if wordSeq.contains(c) then 
                            
                            if wordSeq(index) == wordGuess(index) then  //om bokstaven är på rätt plats
                                
                                drawLetterBlock((charPos(0) + 1, index + 1) , c, State.RightPlace, 0)
                                correctApply(index) = c
                                index += 1 
                                updateKeyboard(c, State.RightPlace)
                                
                            else                                           // om bokstaven finns men är på fel plats
                                drawLetterBlock((charPos(0) + 1, index + 1), c, State.WrongPlace, 0)
                                index += 1
                                if !correctApply.contains(c) then updateKeyboard(c, State.WrongPlace)
                                
                        else                                               //om bokstaven inte finns
                            drawLetterBlock((charPos(0) + 1, index + 1), c, State.NotExist, 0)
                            index += 1
                            updateKeyboard(c, State.NotExist)
                    ) 
                    if wordGuess.toVector == wordSeq.toVector then gameWon() else      //om gissningen är rätt
                    charPos  = (charPos(0) + 1 , 0)                                   //förflyttar ett steg i matrisen dvs en rad ner
                    guesses -= 1
                
                else println(wordString + " is not a word")
    
    def reset(): Unit = 
        drawGame()    
        drawKeys()
        charPos = (0, 0)
        gameOver = false
        guesses = 6

    def play(): Unit =
        import introprog.PixelWindow

        reset()

        while !gameOver do

            if guesses == 0 then outofGuess else
                window.awaitEvent(10)
                window.lastEventType match
                    case PixelWindow.Event.Undefined => ()
                    case PixelWindow.Event.WindowClosed => gameOver = true
                    case PixelWindow.Event.KeyPressed => handleKey()                     
                    case _ => ()
                



        

  

   




