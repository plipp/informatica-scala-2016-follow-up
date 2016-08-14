import sbt._
import Keys._

object Build extends Build {
    val botDirectory = SettingKey[File]("bot-directory")
    val startServer = TaskKey[Unit]("startServer")
    val deploy = TaskKey[Unit]("deploy")

    val botSettings = Seq[Setting[_]](
        organization := "de.informatica",
        name := "scalatron-bot",
        version := "1.0.0-SNAPSHOT",

        scalaVersion := "2.11.8",
        scalacOptions ++= Seq("-deprecation", "-unchecked"),

        javaOptions += "-Xmx1g", //

        libraryDependencies ++= Seq(
            "org.scalatest" %% "scalatest" % "2.2.5" % "test",
            "junit" % "junit" % "4.12" % "test"),

        botDirectory := file("bots"),

        deploy <<= (botDirectory, name, Keys.`package` in Compile) map { (bots, name, botJar) =>
            val target: File = bots / name
            IO createDirectory target
            IO copyFile (botJar, target / "ScalatronBot.jar")
            println(s"COPIED $botJar =>  $target")
        }

    )

    val bot = Project(
        id = "mybot",
        base = file("."),
        settings = Project.defaultSettings ++ botSettings)
}