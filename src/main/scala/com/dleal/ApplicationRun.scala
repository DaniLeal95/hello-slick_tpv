package com.dleal

import java.sql.Date

import com.dleal.caseClass.Persona
import com.dleal.repositories.PersonaRepositorio
import com.dleal.util.DbConfiguration

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ApplicationRun extends App with DbConfiguration {

  System.setProperty("db_name", "mysqlDB")
  System.setProperty("db_config", "src/main/resources/application.conf")

  val personas = new PersonaRepositorio(config)

  println("*********************")
  println("*********************")
  println("       TPV v.1       ")
  println("*********************")
  println("*********************")

  println();println();println()

  println("Introduzca el nombre de la persona")
  val nombre_persona = Console.readLine()

  println()
  println("Introduzca el apellido de la persona")
  val primer_apellido_persona = Console.readLine()

  println()
  println("Introduzca el segundo apellido de la persona")
  val segundo_apellido_persona = Console.readLine()

  println()
  println("Introduzca el email de la persona")
  val email = Console.readLine()

  println()
  println("Introduzca la direccion de la persona")
  val direccion = Console.readLine()

  var persona: Persona = new Persona(None,Some(nombre_persona),Some(primer_apellido_persona),Some(segundo_apellido_persona),Some(new Date(System.currentTimeMillis())),Some(email),Some(direccion),None)
  persona = personas.insertOne(persona)

  println()
  println("Ha guardado esta persona: " + persona)



}
