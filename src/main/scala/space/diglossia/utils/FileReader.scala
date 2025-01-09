package space.diglossia.utils

import scala.io.Source

object FileReader {

  def read(filePath: String): String = {
    val source = Source.fromFile(s"$filePath")

    val input = try {
      Some(source.getLines().mkString("\n"))
    } catch {
      case e: Throwable => println(e)
        None
    } finally {
      source.close()
    }

    input.get
  }

}
