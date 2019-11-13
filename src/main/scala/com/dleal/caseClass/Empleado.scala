package com.dleal.caseClass

import java.sql.Date

case class Empleado (id_empleado: Option[String], id_persona: Option[String], f_creacion: Option[Date],f_eliminacion: Option[Date]) extends Serializable{


}
