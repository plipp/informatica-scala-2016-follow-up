import org.scalatest.FunSuite
import org.scalatest.Matchers._

// about ScalaTest, FunSuite see
// - http://www.scalatest.org/getting_started_with_fun_suite
// - http://www.scalatest.org/at_a_glance/FunSuite
class MyViewTest08 extends FunSuite {

  test("[VIEW quadratic] Not quadratic view should be rejected") {
    an [IllegalArgumentException] should be thrownBy {
      new MyView("ABCDEFGHIJKLMNOPQRSTUVWXYZ") // has 26 chars: not quadratic!
    }
  }

  test("""[VIEW toString] toString should print in separate lines'""") {
    val myView = new MyView("ABCDEFGHIJKLMNOPQRSTUVWXY")
    myView.toString should be("ABCDE\nFGHIJ\nKLMNO\nPQRST\nUVWXY\n")
  }

  test("""[VIEW at] in the center should be 'M'""") {
    val myView = new MyView("ABCDEFGHIJKLMNOPQRSTUVWXY")
    myView.at(0,0) should be('M')
  }

  test("""[VIEW at] in the left upper corner should be 'A'""") {
    val myView = new MyView("ABCDEFGHIJKLMNOPQRSTUVWXY")
    myView.at(-2,-2) should be('A')
  }

  test("""[VIEW at] in the right bottom corner should be 'Y'""") {
    val myView = new MyView("ABCDEFGHIJKLMNOPQRSTUVWXY")
    myView.at(2,2) should be('Y')
  }

  test("""[VIEW at] pos outside should cause an IndexOutOfBoundsException""") {
    val myView = new MyView("ABCDEFGHIJKLMNOPQRSTUVWXY")
    an [IndexOutOfBoundsException] should be thrownBy {
      myView.at(3, 2)
    }
  }
}
