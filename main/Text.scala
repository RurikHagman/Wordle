package wdl

case class Text(source: String):
  lazy val words: Vector[String] = {

    val sourceEncFix = source.map(x => 
      /*
      if x == 'å' then 'a'
      else if x == 'ä' then 'a'
      else if x == 'ö' then 'o'
      */
      if x == 'é' then 'e'
      else x
    
    )

    val sourceFilter = sourceEncFix.map(x => if x.isLetter then x else ' ').split(' ').filter(_ != "")

    sourceFilter.map(x => x.toLowerCase()).toVector

  }  // dela upp source i ord

  lazy val distinct: Vector[String] = words.distinct

  lazy val wordSet: Set[String] = words.toSet

  lazy val wordsOfLength: Map[Int, Set[String]] = wordSet.groupBy(_.length)

  lazy val wordFreq: Map[String, Int] = FreqMapBuilder.apply(words*).toMap  // använd FreqMapBuilder

  def ngrams(n: Int): Vector[Vector[String]] = words.sliding(n).toVector   // använd sliding

  def randomElem[T](xs: Seq[T]): T = xs(scala.util.Random.between(0, (xs.length)))

  def randomWord: String = randomElem(words)

 


object Text:
  def fromFile(fileName: String, encoding: String = "UTF-8"): Text =
    val source = scala.io.Source.fromFile(fileName, encoding)
    val txt = try source.mkString finally source.close()
    Text(txt)
  
  def fromURL(url: String, encoding: String = "UTF-8"): Text =
    val source = scala.io.Source.fromURL(url, encoding)
    val txt = try source.mkString finally source.close()
    Text(txt)

