import Dependencies._

ThisBuild / scalaVersion := "2.12.8"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "faker"

lazy val root = (project in file("."))
  .settings(
    name := "fake-generator",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.github.javafaker" % "javafaker" % "1.0.2",
      "com.github.tototoshi" %% "scala-csv" % "1.3.6"
    )
  )
