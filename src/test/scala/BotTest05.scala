import org.scalatest.FunSuite
import org.scalatest.Matchers._

// about ScalaTest, FunSuite see
// - http://www.scalatest.org/getting_started_with_fun_suite
// - http://www.scalatest.org/at_a_glance/FunSuite
class BotTest05 extends FunSuite {
  val bot = new ControlFunction

  test("[ON React] The bot should move up and have its current energy state as status-message, if there are no impediments.") {
    val serverInput: String = "React(generation=0,time=0,view=_________,energy=100)"

    val reply: String = bot.respond(serverInput)
    reply.should(be("Move(direction=-1:-1)|Status(text=100)"))
  }

  test("""[ON Goodbye] The bot should reply with "", if the Operation Code is 'GoodBye' and not 'React'""") {
    val serverInput: String = "Goodbye(energy=int)"

    val reply: String = bot.respond(serverInput)
    reply should be("")
  }

  test("""[ON Welcome] The bot should reply with "", if the Operation Code is 'Welcome' and not 'React'""") {
    val serverInput: String = "Welcome(name=UserBot,apocalypse=50,round=60,maxslaves=0)"

    val reply: String = bot.respond(serverInput)
    reply shouldBe empty
  }
}
