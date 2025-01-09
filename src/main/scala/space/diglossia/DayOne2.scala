package space.diglossia

import space.diglossia.utils.FileReader

object DayOne2 {

  val input  = FileReader.read("src/main/resources/input_1_1.txt")

  val (locations1, locations2, frequenciesR) =
    input.split("\n").foldLeft(List.empty[Int], List.empty[Int], Map.empty[Int, Int]) { (acc, curr) =>
      val (l1, l2, frequencies) = acc
      val split = curr.split("""[\s]+""")
      val rightCount = frequencies.get(split(1).toInt)
      (l1 :+ split.head.toInt, l2 :+ split(1).toInt,
        rightCount.map(n => frequencies.updated(split(1).toInt, n+1 )).getOrElse(frequencies + (split(1).toInt -> 1)))
    }

  val (sorted1, sorted2) = (locations1.sorted, locations2.sorted)

  val scores: Seq[(Int, Int)] = locations1.map(i => (i, frequenciesR.getOrElse(i, 0)))

  val res  = scores.foldLeft(0)((acc, curr) => acc + curr._1 * curr._2)

  @main def resultDayOne2 = println(res)

}
