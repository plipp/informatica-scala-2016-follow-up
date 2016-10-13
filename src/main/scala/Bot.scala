import Entities.{Bot, Wall}

// Tutorial Bot Class

class ControlFunction {

  // The only door to the EXTERNAL world:
  //
  // Callback function, which is always called, when anything in the world around changes.
  def respond(input: String) = {
    val (opCode: String, paramMap: Map[String, String]) = parse(input)

    if (opCode=="React") /* e.g. React(generation=0,time=0,view=__W_W_W__,energy=100) */ {
      val energy = paramMap("energy")
      val view = paramMap("view")
      val (x,y) = findPath(view)
      s"Move(direction=$x:$y)|Status(text=$energy)"
    } else ""
  }

  private def findPath(view: String) = {
    val pathFinder = new PathFinder(new MyView(view))
    pathFinder.findPath()
  }

  private def parse(input: String): (String, Map[String, String]) = {
    val tokens: Array[String] = input.split('(')
    val opCode: String = tokens(0)

    val params: Array[String] = tokens(1).dropRight(1).split(',')
    val paramMap: Map[String, String] =
      params
        .map(param => param.split('='))
        .map(kv => (kv(0), kv(1))).toMap

    (opCode, paramMap)
  }
}

class PathFinder (view: MyView) {
  def findPath() = {
    def canMoveTo(cell: Cell) = cell.content.map {
      entity => entity match  {
        case Wall | Bot => false
        case _ => true
      }
    }.getOrElse(true)

    val possibleTargetCells: Seq[(Int, Int)] = for {
      x <- -1 to 1
      y <- -1 to 1 if canMoveTo(view.at(x,y))
    } yield (x,y)

    val firstFreeCell: (Int, Int) = ???
    println (s"FREE CELL: ${firstFreeCell}, CHAR = ${view.at(firstFreeCell._1,firstFreeCell._2)}")
    firstFreeCell
  }

}

class MyView (val view: String){
  val size = scala.math.sqrt(view.length).round.toInt
  val n = size/2

  require(size*size==view.length, s"length of view is not quadratic: ${view.length} != $size*$size")


  def at(x: Int, y:Int):Cell = {
    Cell (Entities.abbreviationToEntity.get(view.charAt(toIndex(x, y))))
  }

  private def toIndex(x: Int, y: Int): Int = (n+y)*size + (n+x)

  override def toString: String = {
    def toLines(rest:String): String =
      if (rest.isEmpty) ""
      else {
        val (line, remainingLines) = rest.splitAt(size)
        line + "\n" +toLines(remainingLines)
      }
    toLines(view)
  }
}

// ----------------------------------------------------------------------------------
// INTERNALS (you don't need to touch this during the workshop!)
//
// Entry Point for the Server

class ControlFunctionFactory {
  def create = new ControlFunction().respond _
}

