import org.scalatest.FunSuite
import org.scalatest.Matchers._

class CellTest extends FunSuite {
  test("length of a position should be calculated as manhattan dist to center") {
    Position(3, 5).manhattanLength shouldEqual 8
    Position(-3, 5).manhattanLength shouldEqual 8
    Position(3, -5).manhattanLength shouldEqual 8
    Position(-3, -5).manhattanLength shouldEqual 8
  }
  test("Cells are ordered according to their length (=manhattan dist to center) and boost factor (pos descending)") {
    val cells = Seq(
      Cell(Position(12, 13), Some(Entities.Fluppet)),
      Cell(Position(12, -13), Some(Entities.Snorg)),
      Cell(Position(-2, 1), Some(Entities.Fluppet)),
      Cell(Position(2, -1), Some(Entities.Bot)),
      Cell(Position(2, 1), Some(Entities.Zugar)),
      Cell(Position(0, 0), Some(Entities.Wall)),
      Cell(Position(-12, -13), Some(Entities.Toxifera)),
      Cell(Position(-12, 13), Some(Entities.Zugar))
    )

    val expectedOrderedCells = Seq(
      Cell(Position(0, 0), Some(Entities.Wall)),
      Cell(Position(-2, 1), Some(Entities.Fluppet)),
      Cell(Position(2, 1), Some(Entities.Zugar)),
      Cell(Position(2, -1), Some(Entities.Bot)),
      Cell(Position(12, 13), Some(Entities.Fluppet)),
      Cell(Position(-12, 13), Some(Entities.Zugar)),
      Cell(Position(-12, -13), Some(Entities.Toxifera)),
      Cell(Position(12, -13), Some(Entities.Snorg))
    )

    cells.sorted(Cell.ByLengthAscAndBoostDesc) shouldEqual expectedOrderedCells
  }

  test("Cells are ordered according to their length (=manhattan dist to center) and boost factor (pos ascending)") {
    val cells = Seq(
      Cell(Position(12, 13), Some(Entities.Fluppet)),
      Cell(Position(12, -13), Some(Entities.Snorg)),
      Cell(Position(-2, 1), Some(Entities.Fluppet)),
      Cell(Position(2, -1), Some(Entities.Bot)),
      Cell(Position(2, 1), Some(Entities.Zugar)),
      Cell(Position(0, 0), Some(Entities.Wall)),
      Cell(Position(-12, -13), Some(Entities.Toxifera)),
      Cell(Position(-12, 13), Some(Entities.Zugar))
    )

    val expectedOrderedCells = Seq(
      Cell(Position(0, 0), Some(Entities.Wall)),
      Cell(Position(2, -1), Some(Entities.Bot)),
      Cell(Position(2, 1), Some(Entities.Zugar)),
      Cell(Position(-2, 1), Some(Entities.Fluppet)),
      Cell(Position(12, -13), Some(Entities.Snorg)),
      Cell(Position(-12, -13), Some(Entities.Toxifera)),
      Cell(Position(-12, 13), Some(Entities.Zugar)),
      Cell(Position(12, 13), Some(Entities.Fluppet))
    )

    cells.sorted(Cell.ByLengthAscAndNegBoostAsc) shouldEqual expectedOrderedCells
  }
}
