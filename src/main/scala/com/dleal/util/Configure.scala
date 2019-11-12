package com.dleal.util

import java.io.{File, PrintWriter}
import java.net.URL
import java.nio.file.{Files, Path, Paths, StandardCopyOption}

import scala.io.Source

object Configure {


  def test():Unit = {

    val SapplicationConfOriginal: String = "application.conf" //Original File
    val FapplicationConfOriginal: File = new File(SapplicationConfOriginal)
    val temp: File = new File("tmp/application.conf_CLOUD.tmp") // Temporal File

    val writter: PrintWriter = new PrintWriter(temp)
    Source.fromFile(SapplicationConfOriginal)
      .getLines()
      .foreach(line => writter.println(line))


    writter.close()
    temp.renameTo(FapplicationConfOriginal)

  }

  def test2():Unit = {

    val PapplicationConfOriginal: Path = Paths.get(new File("src/main/resources/application.conf").getPath) //Original File
    val PapplicationConfTest: Path = Paths.get( new File("src/main/resources/tconf/application.conf").getPath)//Testing File

    Files.copy(PapplicationConfTest,PapplicationConfOriginal,StandardCopyOption.REPLACE_EXISTING)

  }
}
