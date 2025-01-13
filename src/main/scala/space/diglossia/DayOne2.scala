package space.diglossia

import utils.FileReader

object DayOne2 {

  val input  = FileReader.read("src/main/resources/input_1_1.txt")

  val (locationsL, frequenciesR) =
    input.split("\n").foldLeft(List.empty[Int], Map.empty[Int, Int]) { (acc, curr) =>
      val (l1, frequencies) = acc
      val split = curr.split("""[\s]+""")
      val rLocation = split(1).toInt
      val rightCount = frequencies.get(rLocation)
      (l1 :+ split.head.toInt,
        rightCount.map(n => frequencies.updated(rLocation, n+1 )).getOrElse(frequencies + (rLocation -> 1)))
    }

  val scores: Seq[(Int, Int)] = locationsL.map(i => (i, frequenciesR.getOrElse(i, 0)))

  val res  = scores.foldLeft(0)((acc, curr) => acc + curr._1 * curr._2)

  @main def resultDayOne2 = {
    println(s"Solution = $res")
    println("The correct answer was 21024792")
  }
}
