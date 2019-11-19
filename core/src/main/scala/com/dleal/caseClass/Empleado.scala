package com.dleal.caseClass

import java.sql.Timestamp

case class Empleado (id_empleado: Option[Int], id_persona: Option[Int], f_creacion: Option[Timestamp],f_eliminacion: Option[Timestamp]) extends Serializable{


}
