import Entities.{Bot, Hidden, Wall}

import scala.util.Random

// Tutorial Bot Class

class ControlFunction {

  // The only door to the EXTERNAL world:
  //
  // Callback function, which is always called, when anything in the world around changes.
  def respond(input: String) = {
    val (opCode: String, paramMap: Map[String, String]) = parse(input)

    if (opCode == "React") /* e.g. React(generation=0,time=0,view=__W_W_W__,energy=100) */ {
      val energy = paramMap("energy")
      val view = paramMap("view")
      val pos = findPath(view)
      s"Move(direction=${pos.x}:${pos.y})|Status(text=$energy)"
    } else ""
  }

  private def findPath(view: String): Position = {
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

class PathFinder(view: MyView) {

  def findPath() = {
    val visibleCells = reachableCellsInRange(-view.n to view.n, -view.n to view.n)

    val nearestCellWithPosBoost =
      visibleCells.filter(_.boost > 0).sorted(Cell.ByLengthAscAndBoostDesc)
        .headOption

    lazy val nearestCellWithNegBoost =
      visibleCells.filter(_.boost < 0).sorted(Cell.ByLengthAscAndNegBoostAsc)
        .headOption

    val targetCandidate = nearestCellWithPosBoost.map(cell => withLogging("nearestCellWithPosBoost",cell.pos.norm))
      .getOrElse(nearestCellWithNegBoost.map(cell => withLogging("nearestCellWithNegBoost",cell.pos.norm.invert))
        .getOrElse(withLogging("nextFree",findFree())))


    val target = if (withLogging("neighbourIsNotFree",!freeNeighbours.contains(targetCandidate))) findFree() else targetCandidate
    println(s"-> TARGET CELL: $target in \n$view\n")
    target
  }

  def reachableCellsInRange(xRange: Range, yRange: Range): Seq[Cell] = {
    for {
      x <- xRange
      y <- yRange
      c = view.at(x, y) if isReachable(c)
    } yield c
  }

  private val freeNeighbours: Set[Position] =
    reachableCellsInRange(-1 to 1, -1 to 1).map(_.pos).toSet

  private def isReachable(cell: Cell) = cell.content.forall {
    entity => entity match {
      case Wall | Bot | Hidden => false
      case _ => true
    }
  }

  private def findFree(): Position = {
    val fnl = freeNeighbours.toList
    val i = Random.nextInt(fnl.size)
    fnl(i)
  }

  private def withLogging[T](msg: String, value: T): T = {
    println(s"$msg: $value")
    value
  }
}

class MyView(val view: String) {
  val size = scala.math.sqrt(view.length).round.toInt
  val n = size / 2

  val center = Position(0, 0)

  require(size * size == view.length, s"length of view is not quadratic: ${view.length} != $size*$size")


  def at(x: Int, y: Int): Cell = {
    Cell(Position(x, y), Entities.abbreviationToEntity.get(view.charAt(toIndex(x, y))))
  }

  private def toIndex(x: Int, y: Int): Int = (n + y) * size + (n + x)

  override def toString: String = {
    def toLines(rest: String): String =
      if (rest.isEmpty) ""
      else {
        val (line, remainingLines) = rest.splitAt(size)
        line + "\n" + toLines(remainingLines)
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

