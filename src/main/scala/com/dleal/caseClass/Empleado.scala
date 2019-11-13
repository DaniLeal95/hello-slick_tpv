package com.dleal.caseClass

import java.sql.Date

case class Empleado (id_empleado: Option[Int], id_persona: Option[Int], f_creacion: Option[Date],f_eliminacion: Option[Date]) extends Serializable{


}
