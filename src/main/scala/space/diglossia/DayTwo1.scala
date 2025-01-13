package space.diglossia

import utils.FileReader


object DayTwo1 {

  val input  = FileReader.read("src/main/resources/input_2_1.txt")

  val isIncreasing: (Int, Int) => Boolean = (a, b) => b - a > 0

  val reports = input.split("\n").map(l => l.split("""[\s]+""").map(_.toInt))

  val safetyReports = reports.map(r => checkReport(r))

  def isSafe(a: Int, b: Int, isIncreasingReport: Boolean): Boolean = {
    if (isIncreasing(a, b) != isIncreasingReport)
      false
    else
      val diff = Math.abs(b - a)
      if diff >= 1 && diff <= 3 then true ; else false
  }

  def checkReport(values: Array[Int]): Seq[Boolean] = {
    val isIncreasingReport = isIncreasing(values.head, values(1))
    for (i <- Range(0, values.length -1)) yield {
      isSafe(values(i), values(i+1), isIncreasingReport)
    }
  }

  def res = safetyReports.count(bools => !bools.contains(false))

  @main def resultDayTwo1 = {
    println(s"Solution = $res")
    println("The correct answer was 341")
  }
  

}

