package com.dleal.repositories

import com.dleal.caseClass.Persona
import com.dleal.tables.PersonasTable
import com.dleal.util.Db
import slick.backend.DatabaseConfig
import slick.dbio.DBIOAction
import slick.driver.JdbcProfile

import scala.collection.mutable.ListBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration


class PersonaRepositorio (val config: DatabaseConfig[JdbcProfile])
  extends Db with PersonasTable
{

  import config.driver.api._
  import scala.concurrent.ExecutionContext.Implicits.global


  def init() = db.run(DBIOAction.seq(personas.schema.create))
  def drop() = db.run(DBIOAction.seq(personas.schema.drop))

  /*
   * Insert Function : Insert One Person
   * */
  def insertOne(person: Persona, single: Boolean = true):Persona = {
    Await.result(db.run(personas returning personas.map(_._id_persona) += person).map( id => person.copy(id_persona = Some(id))),Duration.Inf)
  }

  /*
  * Insert Function : Insert Various Persons
  * */
  def insertMultiple(person: ListBuffer[Persona]): ListBuffer[Persona] = {

    val bufferAux: ListBuffer[Persona] = ListBuffer()
    person.foreach(p => {
      bufferAux += insertOne(p, single = false)
    })
    bufferAux
  }

  /**
    * Get all Persons
    */
  def selectAll(): ListBuffer[Persona] = {
    //      createConnection()
    val allPersons: ListBuffer[Persona] = ListBuffer()
    try {


      allPersons ++= Await.result(db.run(personas.result), Duration.Inf)

    } /*finally closeConnection()*/

    allPersons
  }

  /**
    * Get all Persons
    */
  def selectOne(id: Int): Option[Persona] = {
    //      createConnection()
    var person: Option[Persona] = None
    try {
      person = Await.result(db.run(personas.filter(_._id_persona === id).result.headOption), Duration.Inf)

    } /*finally closeConnection()*/

    person
  }

  /**
    * Filter
    */
  def find(person: Persona): Seq[Persona] = {

    /*val getResult = GetResult(r => Persona(Some(r.nextInt), Some(r.nextString), Some(r.nextString), Some(r.nextString),
      Some(r.nextDate()), Some(r.nextString()), Some(r.nextString()), Some(r.nextBlob())))*/



    //      createConnection()
    try {

      val listaPersona = Await.result(db.run(personas.filter { p =>

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
          .collect({ case Some(criteria) => criteria })
          .reduceLeftOption(_ && _).getOrElse(true: Rep[Boolean])
      }.result), Duration.Inf)


      listaPersona
    } /*finally closeConnection()*/
  }


  /*
  * Update
  * */
  def update(person: Persona ): Int = {
    Await.result(db.run(personas.filter(_._id_persona === person.id_persona)
      .update(person)),Duration.Inf)
  }


  /*
  * Delete
   */
  def delete(idPersona: Int) = {
    Await.result(db.run(personas.filter(_._id_persona === idPersona).delete),Duration.Inf)
  }

}
