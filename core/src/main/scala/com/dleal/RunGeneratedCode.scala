package com.dleal

import java.io.File
import java.util.concurrent.TimeUnit

import com.typesafe.config.ConfigFactory
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver
import slick.jdbc.meta.MTable
import slick.codegen.SourceCodeGenerator

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}


object RunGeneratedCode extends App{

  val slickDriver = "slick.driver.MySQLDriver"
  val jdbcDriver = "org.mysql.Driver"
//  val url = "jdbc:postgresql://localhost:5555/testdb?searchpath=public"
  val outputDir = "src/main/resources/generatedClasses"
  val pkg = "folder1.folder2"
  val username = "postgres"
  val password = "xxx"


  val config: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig("mysqlDB")
  val db: JdbcProfile#Backend#Database = config.db

  implicit val _ec: ExecutionContext = db.ioExecutionContext

  val dbio = MySQLDriver.createModel(Some(MTable.getTables(None, None, None, Some(Seq("TABLE", "VIEW")))))
  val model = db.run(dbio)
  val future : Future[SourceCodeGenerator] = model.map(model => new SourceCodeGenerator(model))
  val codegen : SourceCodeGenerator = Await.result(future, Duration.create(5, TimeUnit.MINUTES))
  codegen.writeToFile(slickDriver, outputDir, pkg, "Tables", "Tables.scala")

  /*slick.codegen.SourceCodeGenerator.run(
    "slick.driver.MySQLDriver", "com.mysql.jdbc.Driver", "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7311673",
      "src/main/resources/generatedClasses/", "com.dleal.codeGen", Some("sql7311673"), Some("kRujnZaQ47"))
*/

}
