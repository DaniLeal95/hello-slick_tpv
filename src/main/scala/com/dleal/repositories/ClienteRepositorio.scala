package com.dleal.repositories

import com.dleal.caseClass.Cliente
import com.dleal.tables.ClientesTable
import com.dleal.util.Db
import org.h2.jdbc.JdbcSQLException
import slick.backend.DatabaseConfig
import slick.dbio.DBIOAction
import slick.driver.JdbcProfile

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class ClienteRepositorio(val config: DatabaseConfig[JdbcProfile])
  extends Db with ClientesTable{

  import config.driver.api._

  import scala.concurrent.ExecutionContext.Implicits.global

  def init(): Future[Unit] = {
    Await.result(db.run(DBIOAction.seq(personas.schema.create)),Duration.Inf)
    db.run(DBIOAction.seq(clientes.schema.create))

  }
  def drop(): Future[Unit] = {
    db.run(DBIOAction.seq(personas.schema.drop))
    db.run(DBIOAction.seq(clientes.schema.drop))
  }

/*
* Insert one Employee
 */
  def insertOne(cliente: Cliente): Cliente = {
    try {
      Await.result(db.run(clientes returning clientes.map(_._id_cliente) += cliente).map(id => cliente.copy(id_cliente = Some(id))), Duration.Inf)
    }
    catch {
      case jdbcSQLException: JdbcSQLException =>  jdbcSQLException.printStackTrace();  cliente
    }
  }

  /*
  * Insert various employees
   */
  def insertVarious(listCliente: List[Cliente]): ListBuffer[Cliente] = {
    val bufferAux: ListBuffer[Cliente] = ListBuffer()

    listCliente.foreach( bufferAux += insertOne(_))

    bufferAux
  }

  /*
  * Get Employee by Id
   */
  def selectOne(idCliente: Int) : Option[Cliente] = {

    var cliente: Option[Cliente] = None

    try {
     cliente = Await.result(db.run(clientes.filter(_._id_cliente === idCliente).result.headOption), Duration.Inf)
    }

    cliente
  }


  /*
  * Get All Employees
   */
  def selectAll(): List[Cliente] = {
    val allClientes = Await.result(db.run(clientes.result), Duration.Inf)
    allClientes.toList
  }

  /*
  * Get Filter Employee
   */
  def find( cliente: Cliente): List[Cliente] = {

    val listClientes: Seq[Cliente] = Await.result( db.run(
      clientes.filter{ e =>

      val list = List(
          cliente.id_persona.map(e._id_persona === _),
          cliente.f_creacion.map(e._f_creacion === _),
          cliente.f_eliminacion.map(e._f_eliminacion === _)
        )

      list.collect({ case Some(criteria) => criteria })
        .reduceLeftOption(_ && _).getOrElse(true: Rep[Boolean])
      }.result
    ),Duration.Inf)

    listClientes.toList
  }

  /*
  * Update
   */

  def update( cliente: Cliente): Int = {
//    try{
      Await.result(db.run(clientes.filter(_._id_cliente === cliente.id_cliente).update(cliente)),Duration.Inf)
//    }
//    catch {
//      case jdbcSQLException: JdbcSQLException =>  jdbcSQLException.printStackTrace(); 0
//    }
  }

  /*
  * Delete
   */
  def delete( id_cliente: Int) : Int = {
    Await.result(db.run(clientes.filter(_._id_cliente === id_cliente).delete),Duration.Inf)
  }

}
