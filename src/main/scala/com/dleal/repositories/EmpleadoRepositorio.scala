package com.dleal.repositories

import com.dleal.tables.EmpleadosTable
import com.dleal.util.Db
import slick.backend.DatabaseConfig
import slick.dbio.DBIOAction
import slick.driver.JdbcProfile

class EmpleadoRepositorio (val config: DatabaseConfig[JdbcProfile])
  extends Db with EmpleadosTable{


  import config.driver.api._
/*
  import scala.concurrent.ExecutionContext.Implicits.global

  def init() = {
    db.run(DBIOAction.seq(personas.schema.create))
    db.run(DBIOAction.seq(empleados.schema.create))

  }
  def drop() = {
    db.run(DBIOAction.seq(personas.schema.drop))
    db.run(DBIOAction.seq(empleados.schema.drop))
  }
*/


}
