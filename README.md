scala-Workshop: Follow Up
=========================

## Starting Point
The current bot implementation of the [Scala Basic Workshop](https://github.com/plipp/informatica-scala-2016) is quite
dumb: 
- it just tries to avoid sticking at walls, but once trapped in a U-like wall-fragment it just moves back and force
and can't escape any more. 
- it only knows about 'W' (Walls) and is not aware of good plants, bad plants, ... 

## Follow up Exercise(s)

### 1. Energy Saving Bot
Make the Bot more aware of its environment, so that its `energy` stays as high as possible. E.g. eat good plants, avoid
bad plants ... 

For the existing kinds of plants see TODO

#### Implementation Hints/Details
Check out the Scala-documentation (e.g. TODO) for `sealed traits` and `case classes`. Use matchers in your implementation.

### 2. [Optional] Find a Strategy how to escape from a U-Wall
TODO

### 3. [Optional] Destroy your Enemies
TODO

## Expected Outcome
- Unit Tests for your implementation (existing samples: `BotTest05`, `MyViewTest08`). Maybe you even want to try out
  Test Driven Development - test first, then implement.  
- code (of course)
- a **short** README.md, which describes
    - how to run the bot (**short** + you may just link to or copy from the 
      the [Bot Development README](/home/plipp/work/My/informatica/informatica-scala-2016-follow-up/docs/bot-development/readme.md))
    - special configurations (number of plants, size of the board ...) of your Scalatron Server, if required. 
    - which strategy and/or implementation you have choosen (diagrams are welcome, but not necessary) and why. // TODO: UML
    
  Again: The README.md can be very short: It just should contain the content one needs to understand, how to run, and how the code works.
  
  
####################################################################################################################
// TODO --> Mail

### How to pass the Results
- Either you fork the `informatica-scala-2016-follow-up`-repo, develop the more intelligent Bot there and make it public
  or give me access
- or you zip the results, upload them to your Dropbox/Google-Drive and give me access
- or you find another way ...

### Final Date
Sept 14th (you of course can pass the results earlier)

If this follow up exercise is to time consuming, please let me know. TODO

  ####################################################################################################################
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