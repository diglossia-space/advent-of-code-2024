package space.diglossia

import utils.FileReader


object DayOne1 {

  val input  = FileReader.read("src/main/resources/input_1_1.txt")

  val (locations1, locations2) = input.split("\n").foldLeft((List.empty[Int], List.empty[Int])) { (acc, curr) =>
    val split = curr.split("""[\s]+""")
    (acc._1 :+ split.head.toInt, acc._2 :+ split(1).toInt)
  }

  val (sorted1, sorted2) = (locations1.sorted, locations2.sorted)
  val diffs: Seq[Int] = for (i <- Range(0, sorted1.length, 1)) yield {
    Math.abs(sorted1(i) - sorted2(i))
  }

  val res  = diffs.sum

  @main def resultDayOne1 ={
    println(s"Solution = $res")
    println("The correct answer was 1879048")
  }

}

