package wdl
import introprog.BlockGame.*

object State extends Enumeration:
    val Idle = java.awt.Color(200,200,200)
    val NotExist = java.awt.Color(140,140,140)
    val WrongPlace = java.awt.Color(235, 177, 52)
    val RightPlace = java.awt.Color(18, 176, 20)

val blockSize = 70
val window = introprog.PixelWindow(7 * blockSize, 11 * blockSize, "Wordle", java.awt.Color(200,200,200))

def drawLetterBlock(pos: (Int, Int), letter: Char, state: java.awt.Color, size: Int): Unit = 
    val x = pos(1) * (blockSize - size)
    val y = pos(0) * (blockSize - size)
    val offsetMap: Map[Char, Int] = Map('Q' -> 9, 'W' -> 6, 'E' -> 10, 'R' -> 9, 'T'-> 10, 'Y' -> 10, 'U' -> 10, 'I' -> 20, 'O' -> 8, 'P' -> 10, 'A' -> 8, 'S' -> 10, 'D' -> 10, 'F' -> 12, 'G' -> 7, 'H' -> 8, 'J' -> 13, 'K' -> 10, 'L' -> 12, 'Z' -> 10, 'X' -> 9, 'C' -> 7, 'V' -> 10, 'B' -> 10, 'N' -> 9, 'M' -> 8)
    window.fill(x, y , (blockSize - size) , (blockSize - size) , State.Idle)
    window.fill((x + 3), (y + 3), ((blockSize - size) - 6), ((blockSize - size) - 6), java.awt.Color(110,110,110))
    window.fill((x + 6), (y + 6), ((blockSize - size) - 12), ((blockSize - size) - 12), state)
    if offsetMap.contains(letter) then
        window.drawText(letter.toString, (x + offsetMap(letter)) , y, java.awt.Color(100,100,100), blockSize - size - 10, 1, "Nunito")
    
    
def drawGame(): Unit = 
    for x <- 1 to 5 do 
        for y <- 1 to 6 do 
            drawLetterBlock((y, x), ' ', java.awt.Color(200,200,200), 0)


val row1: Vector[Char] = Vector('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I' ,'O', 'P')
val row2: Vector[Char] = Vector('A', 'S','D', 'F', 'G', 'H', 'J', 'K', 'L')
val row3: Vector[Char] = Vector('Z', 'X', 'C', 'V', 'B', 'N', 'M')
val alfabet: Vector[Char] = row1 ++ row2 ++ row3

def drawKeys(): Unit = 
    row1.foreach(c => drawLetterBlock((12, row1.indexOf(c)), c, State.Idle, 21))
    row2.foreach(c => drawLetterBlock((13, row2.indexOf(c)), c, State.Idle, 21))
    row3.foreach(c => drawLetterBlock((14, row3.indexOf(c) + 1), c, State.Idle, 21))
