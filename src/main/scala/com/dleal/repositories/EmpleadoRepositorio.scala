package com.dleal.repositories

import com.dleal.caseClass.Empleado
import com.dleal.tables.EmpleadosTable
import com.dleal.util.Db
import slick.backend.DatabaseConfig
import slick.dbio.DBIOAction
import slick.driver.JdbcProfile

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class EmpleadoRepositorio (val config: DatabaseConfig[JdbcProfile])
  extends Db with EmpleadosTable{

  import config.driver.api._
  import scala.concurrent.ExecutionContext.Implicits.global

  import config.driver.api._

  def init() = {
    Await.result(db.run(DBIOAction.seq(personas.schema.create)),Duration.Inf)
    db.run(DBIOAction.seq(empleados.schema.create))

  }
  def drop() = {
    db.run(DBIOAction.seq(personas.schema.drop))
    db.run(DBIOAction.seq(empleados.schema.drop))
  }

/*
* Insert one Employee
 */
  def insertOne(empleado: Empleado):Empleado = {
    Await.result(db.run(empleados returning empleados.map(_._id_empleado) += empleado).map( id => empleado.copy(id_empleado = Some(id))),Duration.Inf)
  }

  /*
  * Insert various employees
   */
  def insertVarious(listEmpleado: List[Empleado]): ListBuffer[Empleado] = {
    val bufferAux: ListBuffer[Empleado] = ListBuffer()

    listEmpleado.foreach( bufferAux += insertOne(_))

    bufferAux
  }

  /*
  * Get Employee by Id
   */
  def selectOne(idEmpleado: Int) : Option[Empleado] = {

    var empleado: Option[Empleado] = None

    try {
     empleado = Await.result(db.run(empleados.filter(_._id_empleado === idEmpleado).result.headOption), Duration.Inf)
    }

    empleado
  }


  /*
  * Get All Employees
   */
  def selectAll(): List[Empleado] = {
    val allEmpleados = Await.result(db.run(empleados.result), Duration.Inf)
    allEmpleados.toList
  }

  /*
  * Get Filter Employee
   */
  def find( empleado: Empleado): List[Empleado] = {

    val listEmpleados: Seq[Empleado] = Await.result( db.run(
      empleados.filter{ e =>

      val list = List(
          empleado.id_persona.map(e._id_persona === _),
          empleado.f_creacion.map(e._f_creacion === _),
          empleado.f_eliminacion.map(e._f_eliminacion === _)
        )

      list.collect({ case Some(criteria) => criteria })
        .reduceLeftOption(_ && _).getOrElse(true: Rep[Boolean])
      }.result
    ),Duration.Inf)

    listEmpleados.toList
  }

  /*
  * Update
   */

  def update( empleado: Empleado): Int = {
    Await.result(db.run(empleados.update(empleado)),Duration.Inf)
  }

  /*
  * Delete
   */
  def delete( id_empleado: Int) : Int = {
    Await.result(db.run(empleados.filter(_._id_empleado === id_empleado).delete),Duration.Inf)
  }

}
