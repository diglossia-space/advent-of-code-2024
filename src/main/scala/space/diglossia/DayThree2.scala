package space.diglossia

import space.diglossia.utils.FileReader


object DayThree {

  val input  = FileReader.read("src/main/resources/input_3_1.txt")

  val mulPattern = """mul\((\d{1,3}),(\d{1,3})\)""".r
  val enablePattern = """do\(\)""".r
  val disablePattern = """don't\(\)""".r


  val multipliers = ( for (m <- mulPattern.findAllMatchIn(input)) yield
    (m.group(1).toInt, m.group(2).toInt)).toList

  println(s"Found ${multipliers.length}  matches")

  val res = multipliers.map((a, b) => b * a).sum

  @main def resultDayThree1 = {
    println(s"Solution = $res")
    println("The correct answer was 165225049")
  }

}

