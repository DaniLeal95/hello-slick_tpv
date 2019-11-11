package com.dleal.filters

import java.sql.{Blob, Date}




case class FiltroPersonas(
                           idPersona: Option[Set[Int]] = None,
                           nombre: Option[Set[String]] = None,
                           primerApellido:Option[Set[String]] = None,
                           segundoApellido: Option[Set[String]] = None,
                           fecha_Nacimiento: Option[Set[Date]] = None,
                           email:Option[Set[String]] = None,
                           direccion: Option[Set[String]] = None,
                           image: Option[Set[Blob]] = None) {



}
