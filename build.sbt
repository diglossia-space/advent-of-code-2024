val scala3Version = "3.6.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "advent-of-code-2024",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
