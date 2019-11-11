package com.dleal.caseClass

import java.sql.{Blob, Date}

case class Persona( id_persona: Option[Int] = None, nombre: Option[String], primerApellido:Option[String],segundoApellido: Option[String],
                    fecha_Nacimiento: Option[Date], email:Option[String], direccion: Option[String], image: Option[Blob] ) extends Serializable {

  override def toString: String = {
    id_persona.getOrElse(None) + "|" + nombre.getOrElse(None) + "|" + primerApellido.getOrElse(None) + "|" + segundoApellido.getOrElse(None) + "|" + image.getOrElse(None)
  }
}
