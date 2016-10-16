import Entities.Entity

import scala.math.abs

case class Position(x: Int, y: Int) {
  def norm: Position = Position(norm(x), norm(y))
  def invert: Position = Position(-x,-y)
  def manhattanLength: Int = abs(x) + abs(y)
  private def norm(x: Int): Int = if (x == 0) 0 else x / abs(x)
}

case class Cell(pos: Position, content: Option[Entity]) {
  def boost: Int = this.content.map(_.energyBoost).getOrElse(0)
}

object Cell {

  object ByLengthAscAndBoostDesc extends Ordering[Cell] {
    override def compare(x: Cell, y: Cell): Int = {
      val byLength = Ordering[Int].compare(x.pos.manhattanLength, y.pos.manhattanLength)
      if (byLength != 0) byLength
      else Ordering[Int].reverse.compare(x.boost, y.boost)
    }
  }
  object ByLengthAscAndNegBoostAsc extends Ordering[Cell] {
    override def compare(x: Cell, y: Cell): Int = {
      val byLength = Ordering[Int].compare(x.pos.manhattanLength, y.pos.manhattanLength)
      if (byLength != 0) byLength
      else Ordering[Int].compare(x.boost, y.boost)
    }
  }

}