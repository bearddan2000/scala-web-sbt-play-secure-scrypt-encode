name := """play-auth"""
organization := "example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  guice,
  "org.bouncycastle" % "bcprov-jdk15on" % "1.65",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)
