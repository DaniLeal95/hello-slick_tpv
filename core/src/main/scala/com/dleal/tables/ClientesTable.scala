package com.dleal.tables

import java.sql.Timestamp
import java.time.LocalDateTime

import com.dleal.caseClass.Cliente
import com.dleal.util.Db
import slick.profile.SqlProfile.ColumnOption.{NotNull, Nullable}

trait ClientesTable extends PersonasTable { this: Db =>
  import config.driver.api._

  class Clientes( tag: Tag)
    extends Table[Cliente] (tag, "Clientes"){

    //Columns
    def _id_cliente : Rep[Int] = column[Int]("id_cliente", O.PrimaryKey, O.AutoInc)
    def _f_creacion: Rep[Timestamp] = column[Timestamp]("d_startDate", O.Default(Timestamp.valueOf(LocalDateTime.now())),Nullable)
    def _f_eliminacion: Rep[Timestamp] = column[Timestamp]("d_endDate",Nullable)

    // Foreign Key
    def _id_persona: Rep[Int] = column[Int] ("id_persona", NotNull)

    def * = (_id_cliente.?, _id_persona.?, _f_creacion.?, _f_eliminacion.?) <> (Cliente.tupled, Cliente.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(_id_cliente), Rep.Some(_id_persona), Rep.Some(_f_creacion), Rep.Some(_f_eliminacion)).shaped.<>({r=>import r._; _1.map(_=> Cliente.tupled((_1, _2, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    def _persona = foreignKey("Clientes_ibfk_1", _id_persona,personas )(_._id_persona,ForeignKeyAction.Cascade,ForeignKeyAction.Cascade)



  }

  val clientes = TableQuery[Clientes]

}
