package com.dleal.repositories

import com.dleal.caseClass.Agenda
import com.dleal.tables.AgendasTable
import com.dleal.util.Db
import org.h2.jdbc.JdbcSQLException
import slick.backend.DatabaseConfig
import slick.dbio.DBIOAction
import slick.driver.JdbcProfile

import scala.collection.mutable.ListBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

class AgendaRepositorio (val config: DatabaseConfig[JdbcProfile])
  extends Db with AgendasTable{

  import config.driver.api._

  import scala.concurrent.ExecutionContext.Implicits.global

  def init(): Unit = {
    Await.result(db.run(DBIOAction.seq(personas.schema.create)),Duration.Inf)
    Await.result(db.run(DBIOAction.seq(empleados.schema.create)),Duration.Inf)
    Await.result(db.run(DBIOAction.seq(agendas.schema.create)),Duration.Inf)
  }

  def drop(): Future[Unit] = {
    db.run(DBIOAction.seq(personas.schema.drop))
    db.run(DBIOAction.seq(empleados.schema.drop))
    db.run(DBIOAction.seq(agendas.schema.drop))
  }

  /*
  * Function Insert One work
   */
  def insertOne(tarea: Agenda): Agenda = {
    try {
      Await.result(db.run(agendas returning agendas.map(_._id_agenda) += tarea).map(id => tarea.copy(id_tarea = Some(id))), Duration.Inf)
    }
    catch {
      case jdbcSQLException: JdbcSQLException =>  jdbcSQLException.printStackTrace();  tarea
    }
  }


  /*
  * Function Insert Various works
  */
  def insertVarious(tareas: List[Agenda]): ListBuffer[Agenda] = {
    val listBuffer: ListBuffer[Agenda] = ListBuffer()

    tareas.foreach(tarea => {
      listBuffer += insertOne(tarea)
    })

    listBuffer
  }

  /*
  * Function get One work By Id
   */
  def selectOne(id_agenda: Int): Option[Agenda] = {
    var agenda: Option[Agenda] = None

    try {
      agenda = Await.result(db.run(agendas.filter(_._id_agenda === id_agenda).result.headOption), Duration.Inf)
    }

    agenda
  }

  /*
  * Function get all agenda table
   */
  def selectAll(): List[Agenda] = {
    val allTareas = Await.result(db.run(agendas.result), Duration.Inf)
    allTareas.toList
  }

  /*
  * Function get Works by Filter
   */
  def find(agenda: Agenda): List[Agenda] = {
    val listAgenda: Seq[Agenda] = Await.result( db.run(
      agendas.filter{ e =>

        val list = List(
          agenda.id_empleado.map(e._id_empleado === _),
          agenda.fecha_desde.map(e._fecha_desde === _),
          agenda.fecha_hasta.map(e._fecha_hasta === _),
          agenda.status.map(e._status === _),
          agenda.descripcion.map(e._descripcion === _),
          agenda.fecha_expiracion.map(e._fecha_expiracion === _),
          agenda.fecha_creacion.map(e._fecha_creacion === _)
        )

        list.collect({ case Some(criteria) => criteria })
          .reduceLeftOption(_ && _).getOrElse(true: Rep[Boolean])
      }.result
    ),Duration.Inf)

    listAgenda.toList
  }

  /*
* Update work by id
 */

  def update( agenda: Agenda): Int = {
    //    try{
    Await.result(db.run(agendas.filter(_._id_agenda === agenda.id_tarea).update(agenda)),Duration.Inf)
    //    }
    //    catch {
    //      case jdbcSQLException: JdbcSQLException =>  jdbcSQLException.printStackTrace(); 0
    //    }
  }

  /*
  * Delete work by id
   */
  def delete( id_tarea: Int) : Int = {
    Await.result(db.run(agendas.filter(_._id_agenda === id_tarea).delete),Duration.Inf)
  }



}
