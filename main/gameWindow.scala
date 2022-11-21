package wdl
import introprog.BlockGame.*

val blockSize = 50
val window = introprog.PixelWindow(10 * blockSize, 15 * blockSize, "Wordle", java.awt.Color(200,200,200))

def drawLetterBlock(pos: (Int, Int), letter: Char, state: java.awt.Color): Unit = 
    val x = pos(0) * blockSize
    val y = pos(1) * blockSize
    window.fill(x, y, blockSize, blockSize, java.awt.Color(150,150,150))
    window.fill((x + 3), (y + 3), (blockSize - 6), (blockSize - 6), state)
    window.drawText(letter.toString, (x + 5), (y - 5), java.awt.Color(0,0,0), blockSize, 1, "Nunito")
    
    


