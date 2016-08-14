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

// NEW
// EXERCISE: Implement findPath, so that the Bot does not crash into a wall
class PathFinder (view: MyView) {
  def findPath() = {
    val cellsWithOtherThanWall: Seq[(Int, Int)] = for {
      x <- -1 to 1
      y <- -1 to 1 if view.at(x,y)!='W' && view.at(x,y)!='M'
    } yield (x,y)

    val firstFreeCell: (Int, Int) = cellsWithOtherThanWall.head
    println (s"FREE CELL: ${firstFreeCell}, CHAR = ${view.at(firstFreeCell._1,firstFreeCell._2)}")
    firstFreeCell
  }

}

// NEW
// EXERCISE: Implement toIndex, so that MyViewTest08 is green
class MyView (val view: String){
  val size = scala.math.sqrt(view.length).round.toInt
  val n = size/2

  require(size*size==view.length, s"length of view is not quadratic: ${view.length} != $size*$size")


  def at(x: Int, y:Int) = view.charAt(toIndex(x,y))

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

