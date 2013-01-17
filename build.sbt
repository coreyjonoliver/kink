seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

name := "kink"

organization := "org.coreyoliver"

version := "0.1"

scalaVersion := "2.9.2"

crossScalaVersions := Seq("2.9.1", "2.9.2")

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
  "net.liftweb" %% "lift-common" % "2.5-M1",
  "net.liftweb" %% "lift-util" % "2.5-M1",
  "net.liftweb" %% "lift-json" % "2.5-M1",
  "net.databinder.dispatch" %% "core" % "0.9.1",
  "org.scalatest" %% "scalatest" % "1.8" % "test",
  "org.rogach" %% "scallop" % "0.7.0",
  "commons-lang" % "commons-lang" % "2.6"
)

parallelExecution in Test := false
