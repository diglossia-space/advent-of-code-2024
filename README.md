## sbt project compiled with Scala 3

### Usage

This is an sbt project for Advent of Code 2024. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

There is an `Object` representing my solution for each assignment and each `Object` has a main method.

There are two ways to run the solutions:

1. Run `sbt run` and then enter the number corresponding to the solution when prompted.
2. Run it directly by specifying the main-annotated method when starting `sbt`, like so `sbt "runMain space.diglossia.resultDay01Part1"`