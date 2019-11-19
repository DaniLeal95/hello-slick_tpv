package com.dleal.caseClass

import java.sql.Timestamp

case class Agenda (id_tarea: Option[Int], id_empleado: Option[Int], fecha_desde: Option[Timestamp], fecha_hasta: Option[Timestamp], status: Option[Char], descripcion: Option[String], fecha_expiracion: Option[Timestamp]
                   , fecha_creacion: Option[Timestamp]){}
