package com.dleal.tables

import java.sql.Date

import com.dleal.caseClass.Empleado
import com.dleal.util.Db
import slick.profile.SqlProfile.ColumnOption.{NotNull, Nullable}

trait EmpleadosTable extends PersonasTable { this: Db =>
  import config.driver.api._

  /*class Empleados( tag: Tag)
    extends Table[Empleado] (tag, "Empleados"){

    //Columns
    def _id_empleado : Rep[Int] = column[Int]("id_empleado", O.PrimaryKey, O.AutoInc)
    def _f_creacion: Rep[Date] = column[Date]("d_startDate", O.Default(new Date(System.currentTimeMillis())),Nullable)
    def _f_eliminacion: Rep[Date] = column[Date]("d_endDate",Nullable)

    // Foreign Key
    def _id_persona: Rep[Int] = column[Int] ("id_persona", NotNull)

    def * = (_id_empleado.? , _id_persona.?,_f_creacion.?,_f_eliminacion.?) <>
      ((Empleado.apply _).tupled,Empleado.unapply)

    def _persona = foreignKey("PERSONA_FK", _id_persona,personas )(_._id_persona, ForeignKeyAction.NoAction,ForeignKeyAction.Cascade)




  }

  val empleados = TableQuery[Empleados]*/

}
