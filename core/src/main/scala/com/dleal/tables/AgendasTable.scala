package com.dleal.tables

import java.sql.Timestamp

import com.dleal.caseClass.Agenda
import com.dleal.util.Db
import slick.profile.SqlProfile.ColumnOption.{NotNull, Nullable}

trait AgendasTable  extends EmpleadosTable {
  this: Db =>
  import config.driver.api._

  class Agendas( tag: Tag)
    extends Table[Agenda] (tag, "Agenda"){

    //Columnas
    def _id_agenda: Rep[Int] = column[Int]("id_tarea", O.PrimaryKey, O.AutoInc)
    def _fecha_desde: Rep[Timestamp] = column[Timestamp]("fecha_desde", NotNull)
    def _fecha_hasta: Rep[Timestamp] = column[Timestamp]("fecha_hasta", NotNull)
    def _status: Rep[Char] = column[Char]("status",O.Default('A'), Nullable)
    def _descripcion: Rep[String] = column[String]("descripcion", NotNull)
    def _fecha_expiracion: Rep[Timestamp] = column[Timestamp]("fecha_expiracion",Nullable)
    def _fecha_creacion: Rep[Timestamp] = column[Timestamp]("fecha_creacion",NotNull)

    //Clave foranea
    def _id_empleado: Rep[Int] = column[Int] ("id_empleado", NotNull)
    def _persona = foreignKey("Agenda_ibfk_1", _id_empleado,empleados )(_._id_empleado,ForeignKeyAction.Cascade,ForeignKeyAction.Cascade)

    def * = (_id_agenda.?, _id_empleado.?,_fecha_desde.?, _fecha_hasta.?,_status.?,_descripcion.?,_fecha_expiracion.?,_fecha_creacion.?) <> (Agenda.tupled, Agenda.unapply)

  }

  val agendas = TableQuery[Agendas]
}
