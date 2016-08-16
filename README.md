scala-Workshop: Follow Up
=========================

## 1. Starting Point
The current bot implementation of the [Scala Basic Workshop](https://github.com/plipp/informatica-scala-2016) is quite
dumb: 
- it just tries to avoid sticking at walls, but once trapped in a room-like wall-fragment, it just moves back and force
and can't escape any more. 
- it only knows about 'W' (Walls) and is not aware of good plants, bad plants, ... 

## 2. Follow up Exercise

### 2.1 Energy Saving Bot
Make the Bot more aware of its environment, so that its `energy` stays as high as possible. E.g. eat good plants, avoid
bad plants ... 

For the existing kinds of plants aka *entities* see the [Scalatron Game Rules](https://github.com/plipp/scalatron/blob/master/Scalatron/doc/markdown/Scalatron%20Game%20Rules.md).

You do not need to use mini-bots for this part of the exercise. This comes later as optional task.

#### Implementation Hints/Details
Check out the Scala-documentation (e.g. [Scala-Tutorials](http://docs.scala-lang.org/tutorials/tour/case-classes)) for `abstract classes` or`sealed traits` and `case classes`. 
Use them and matchers in your implementation.

### 2.2 [Optional] Tasks/Ideas

If you want to dive deeper in the world of Scalatron, check out the [Scalatron Game Rules](https://github.com/plipp/scalatron/blob/master/Scalatron/doc/markdown/Scalatron%20Game%20Rules.md)
and
- send out Mini-Bots (e.g. randomly using `scala.util.Random`)
- try out the single `Strategies`, described in the game rules.

You also might want to
- find out, how to escape from room-like wall-fragments
- play with the [parameters of the server](https://github.com/plipp/scalatron/blob/master/Scalatron/doc/markdown/Scalatron%20Server%20Setup.md#botwar-game-options) (`$informatica-scala-2016-follow-up/server/bin/startScalatronServer.sh`)

But please note: This is really **optional**.

## 3. Expected Outcome
- Unit Tests for your implementation (existing samples: `BotTest05`, `MyViewTest08`). Maybe you even want to try out
  Test Driven Development - test first, then implement.  
  
  Please note, that **all** tests, thus also the existing one, should always run. If you make changes, which affect the existing
  tests, please check, if your changes break anything. If you're change is intended and requires the adaption of a test,
  just fix it.
  
- code (of course)
- a **short** README.md (really in [Markdown](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) format please), which describes
    - how to run the bot (**short** + you may just link to or copy from the 
      the [Bot Development README](/home/plipp/work/My/informatica/informatica-scala-2016-follow-up/docs/bot-development/readme.md))
    - special configurations (number of plants, size of the board ...) of your Scalatron Server, **only** if required. 
    - which strategy and/or implementation you have choosen (diagrams as e.g. [UML class/state/sequence diagrams](https://en.wikipedia.org/wiki/Unified_Modeling_Language) 
      are welcome, but not necessary) and why.
    
  Again: The README.md can be very short: It just should contain the content, one needs to understand, how to run, and how the code works.
  

### Final Date
Sept 14th 2016 (you of course can pass the results earlier)

<hr>
  
# Setting up the Environment

1. Check out this [Scala Workshop Follow Up](https://github.com/plipp/informatica-scala-2016-follow-up):<br>
   `git clone git@github.com:plipp/informatica-scala-2016-follow-up.git` or <br>
   `git clone https://github.com/plipp/informatica-scala-2016-follow-up.git`

2. Import the scala workshop follow-up project into IntelliJ: `informatica-scala-2016-follow-up/build.sbt`<br>
   ... can take some time as it downloads the whole internet ...
   
3. `deploy` the Bot-plugin and start the Scalatron-Server as described in the [Bot Development README](/home/plipp/work/My/informatica/informatica-scala-2016-follow-up/docs/bot-development/readme.md)

# References

- [Scalatron Game Rules](https://github.com/plipp/scalatron/blob/master/Scalatron/doc/markdown/Scalatron%20Game%20Rules.md)
- [Scalatron Game Protocol](https://github.com/plipp/scalatron/blob/master/Scalatron/doc/markdown/Scalatron%20Protocol.md)

- [Scalatron Server Setup](https://github.com/plipp/scalatron/blob/master/Scalatron/doc/markdown/Scalatron%20Server%20Setup.md#botwar-game-options)