package com.dleal.tables

import slick.driver.MySQLDriver.api._
import java.sql.Date
import java.sql.Blob

import com.dleal.caseClass.Persona
import com.dleal.filters.GenericQuery
import slick.driver.MySQLDriver
import slick.jdbc.GetResult
import slick.lifted.{AbstractTable, Tag}

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

class Personas(tag: Tag)
  extends Table[Persona](tag, "personas") {

  private var _db: Option[MySQLDriver.backend.Database] = None
  private implicit var _ec: Option[ExecutionContext] = None



  val _id_persona: Rep[Int] = column[Int]("id_persona", O.PrimaryKey, O.AutoInc)

  val _nombre: Rep[String] = column[String]("nombre")

  val _primerApellido: Rep[String] = column[String]("primerApellido")

  val _segundoApellido: Rep[String] = column[String]("segundoApellido")

  val _fecha_Nacimiento: Rep[Date] = column[Date]("fecha_Nacimiento") //(DateMapper.utilDate2SqlDate)
  val _email: Rep[String] = column[String]("email")

  val _direccion: Rep[String] = column[String]("direccion")

  val _image: Rep[Blob] = column[Blob]("Image")


  // Every table needs a * projection with the same type as the table's type parameter
  def * = (_id_persona.?, _nombre.?, _primerApellido.?, _segundoApellido.?, _fecha_Nacimiento.?, _email.?, _direccion.?, _image.?) <>
    (Persona.tupled, Persona.unapply)



  private def createConnection(): Unit = {
    _db = Some(Database.forConfig("mysqlDB"))
    _ec = Some(_db.get.executor.executionContext)
  }

  private def closeConnection(): Unit = {
    _db.get.close()
    _db = None
    _ec = None
  }

  /*
  * Insert Function : Insert One Person
  * */
  def insertOne(person: Persona, single: Boolean = true): Persona = {

    if (single) createConnection()

    try {
      val personas: TableQuery[Personas] = TableQuery[Personas]
      val insertQuery = personas returning personas.map(_._id_persona) into ((item, id) => item.copy(id_persona = Some(id)))
      val action = insertQuery += person
      val personReturn: Persona = Await.result(_db.get.run(action).map {
        _ match {
          case x: Persona => x
          case _ => Persona.apply(None, None, None, None, None, None, None, None)
        }
      }(_ec.get).recover {
        case e: java.sql.SQLException => Persona.apply(None, None, None, None, None, None, None, None)
      }(_ec.get), Duration.Inf)

      personReturn
    }
    catch {
      case _: Exception =>
        closeConnection()
        Persona.apply(None, None, None, None, None, None, None, None)
    }
    finally {
      if (single) closeConnection()
    }
  }

  /*
  * Insert Function : Insert Various Persons
  * */
  def insertMultiple(person: ListBuffer[Persona]): ListBuffer[Persona] = {

    val bufferAux: ListBuffer[Persona] = ListBuffer()
    createConnection()

    person.foreach(p => {
      bufferAux += insertOne(p, single = false)
    })

    closeConnection()
    bufferAux
  }

  /**
    * Get all Persons
    */
  def selectAll(): ListBuffer[Persona] = {
    createConnection()
    val allPersons: ListBuffer[Persona] = ListBuffer()
    try {
      val personas: TableQuery[Personas] = TableQuery[Personas]

      allPersons ++= Await.result(_db.get.run(personas.result), Duration.Inf)

    } finally closeConnection()

    allPersons
  }

  /**
    * Get all Persons
    */
  def selectOne(id: Int): Option[Persona] = {
    createConnection()
    var person: Option[Persona] = None
    try {
      val personas: TableQuery[Personas] = TableQuery[Personas]

      person = Await.result(_db.get.run(personas.filter(_._id_persona === id).result.headOption), Duration.Inf)

    } finally closeConnection()

    person
  }

  /**
    * Filter
    */
  def find (person: Persona):Seq[Persona] = {

    /*val getResult = GetResult(r => Persona(Some(r.nextInt), Some(r.nextString), Some(r.nextString), Some(r.nextString),
      Some(r.nextDate()), Some(r.nextString()), Some(r.nextString()), Some(r.nextBlob())))*/



    createConnection()
    try {
      val personas: TableQuery[Personas] = TableQuery[Personas]


      val listaPersona = Await.result(_db.get.run(personas.filter{ p =>

      val e = List(
        person.nombre.map(p._nombre === _),
        person.primerApellido.map(p._primerApellido === _),
        person.segundoApellido.map(p._segundoApellido === _),
        person.fecha_Nacimiento.map(p._fecha_Nacimiento === _),
        person.email.map(p._email === _),
        person.direccion.map(p._direccion === _),
        person.image.map(p._image === _)
      )

        e
        .collect({case Some(criteria)  => criteria})
        .reduceLeftOption(_ && _).getOrElse(true: Rep[Boolean])
      }.result),Duration.Inf)


      listaPersona
    } finally closeConnection()
  }






}
