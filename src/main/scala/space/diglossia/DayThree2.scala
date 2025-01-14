package space.diglossia

import space.diglossia.utils.FileReader

import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex


object DayThree {

  val input  = FileReader.read("src/main/resources/input_3_1.txt")

  val mulPattern = """mul\((\d{1,3}),(\d{1,3})\)""".r.unanchored
  val switchPattern = """(don?'?t?\(\))""".r
  val switch = new ListBuffer[Boolean]
  true +=: switch
  val multipliers = new ListBuffer[(Int, Int)]

  def handleSwitch(switching: Regex.Match) = {
    val matched = switching.group(0)
    if matched.contains("n") then
      false +=: switch
    else
      true +=: switch
  }

  def handleMul(mulling: Regex.Match) = {
    if switch.head then
      val a = mulling.group(1)
      val b = mulling.group(2)
      multipliers.addOne(a.toInt, b.toInt)
  }

  def walkThrough(s: String): String = {
    val firstSwitch = switchPattern.findFirstMatchIn(s)
    val firstMul = mulPattern.findFirstMatchIn(s)
    (firstSwitch, firstMul) match {
      case (Some(switching), Some(mulling)) =>
        if switching.start < mulling.start then
          handleSwitch(switching)
          s.substring(switching.end)
        else
          handleMul(mulling)
          s.substring(mulling.end)

      case (Some(switching), None) =>
        handleSwitch(switching)
        s.substring(switching.end)

      case (None, Some(mulling)) =>
        handleMul(mulling)
        s.substring(mulling.end)

      case _ =>
        ""
    }
  }

  def findAllEnabled(s: String): String = {
    val rest = walkThrough(s)
    if (rest.nonEmpty)
      findAllEnabled(rest)
    else
      s
  }


  val res = multipliers.map((a, b) => b * a).sum

  @main def resultDayThree2 = {
    val nothing = findAllEnabled(input)
    println(s"Found ${multipliers.length} enabled matches and switched ${switch.length} times")
    val res = multipliers.map((a, b) => b * a).sum
    println(s"Solution = $res")
    println("The correct answer was 108830766")
  }

}

