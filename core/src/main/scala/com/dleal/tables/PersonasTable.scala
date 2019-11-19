package com.dleal.tables

import slick.driver.MySQLDriver.api._
import java.sql.Date
import java.sql.Blob

import com.dleal.caseClass.Persona
import com.dleal.util.Db
import com.typesafe.config.Config
import javax.sql.DataSource
import slick.driver.MySQLDriver
import slick.lifted.Tag
import slick.profile.SqlProfile.ColumnOption.Nullable

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

trait PersonasTable { this: Db =>
  import config.driver.api._

  class Personas(tag: Tag)
    extends Table[Persona](tag, "Personas") {

    def _id_persona: Rep[Int] = column[Int]("id_persona", O.PrimaryKey, O.AutoInc)

    def _nombre: Rep[String] = column[String]("nombre")

    def _primerApellido: Rep[String] = column[String]("primerApellido")

    def _segundoApellido: Rep[String] = column[String]("segundoApellido", Nullable)

    def _fecha_Nacimiento: Rep[Date] = column[Date]("fecha_Nacimiento") //(DateMapper.utilDate2SqlDate)
    def _email: Rep[String] = column[String]("email")

    def _direccion: Rep[String] = column[String]("direccion")

    def _image: Rep[Blob] = column[Blob]("Image", Nullable)


    // Every table needs a * projection with the same type as the table's type parameter
    def * = (_id_persona.?, _nombre.?, _primerApellido.?, _segundoApellido.?, _fecha_Nacimiento.?, _email.?, _direccion.?, _image.?) <>
      (Persona.tupled, Persona.unapply)

  }


  val personas = TableQuery[Personas]


}
