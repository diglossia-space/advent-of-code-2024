package space.diglossia

import utils.FileReader

import java.io.{File, FileWriter}
import scala.collection.mutable.ListBuffer
import scala.language.postfixOps

object DayTwo2 {

  // When can removing one level make it safe?
  // 1.  If gap too big, drop one Int and it works.
  // 2.  If one outlier in direction, drop one Int and it works

  val input = FileReader.read("src/main/resources/input_2_1.txt")
  val reports = input.split("\n").map(l => l.split("""[\s]+""").map(_.toInt))

  val isIncreasing: (Int) => Boolean = n => n > 0
  val isSafeDiff: Int => Boolean = diff => Math.abs(diff) >= 1 && Math.abs(diff) <= 3

  def isReportSafe(report: Array[Int]): Boolean = {
    val trend = report(1) - report.head
    val safeLevels = new ListBuffer[Boolean]
    true +=: safeLevels
    for (i <- Range(1, report.length)) {
      if (safeLevels.head) {
        val diff = report(i) - report(i - 1)
        if (isIncreasing(diff) != isIncreasing(trend))
          false +=: safeLevels
        if (!isSafeDiff(diff))
          false +=: safeLevels
      }
    }
    safeLevels.head
  }

  def isReportFixable(report: Array[Int]): Boolean = {
    val fixedIfSkip = new ListBuffer[Boolean]
    false +=: fixedIfSkip
    for (i <- Range(0, report.length)) {
      if (!fixedIfSkip.head) {
        val skippingI = report.slice(0, i) ++ report.slice(i +1, report.length)
        val isFixed = isReportSafe(skippingI)
        isFixed +=: fixedIfSkip
      }
    }
    fixedIfSkip.head
  }

  @main def resultDayTwo2 =
    val res = reports.map{r =>
      val safe = isReportSafe(r)
      val fixable =
        if !safe then isReportFixable(r) else false
      (safe, fixable)
    }
    println(s"${res.count(_._1)} reports are SAFE. ${res.count(_._2)} reports are DAMPENED")
    println(s"Solution = ${res.count(r => r._1 || r._2)}")
    println("The correct answer was 404")

}

