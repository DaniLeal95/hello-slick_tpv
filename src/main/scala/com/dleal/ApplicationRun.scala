package com.dleal

import java.sql.{Date, Timestamp}
import java.time.LocalDateTime

import com.dleal.caseClass.{Cliente, Empleado, Persona}
import com.dleal.repositories.{ClienteRepositorio, EmpleadoRepositorio, PersonaRepositorio}
import com.dleal.util.DbConfiguration

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object ApplicationRun extends App with DbConfiguration {

  System.setProperty("db_name", "mysqlDB")
  System.setProperty("db_config", "src/main/resources/application.conf")

  val personas = new PersonaRepositorio(config)
  val empleados = new EmpleadoRepositorio(config)
  val clientes = new ClienteRepositorio(config)

  println("*********************")
  println("*********************")
  println("       TPV v.1       ")
  println("*********************")
  println("*********************")

  println();println();println()

  println()
  println("Es un empleado(E) o un cliente(C)")
  val clienteoEmpleado = Console.readChar()


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

  var persona: Persona = Persona(None,Some(nombre_persona),Some(primer_apellido_persona),Some(segundo_apellido_persona),Some(new Date(System.currentTimeMillis())),Some(email),Some(direccion),None)
  persona = personas.insertOne(persona)

  println()
  println("Ha guardado esta persona: " + persona)


  if (clienteoEmpleado.equals('E')) {
    var empleado: Empleado = Empleado(None, persona.id_persona, Some(Timestamp.valueOf(LocalDateTime.now())), None)
    empleado = empleados.insertOne(empleado)
    println("Ha guardado este empleado: " + empleado)
  }else{
    var cliente: Cliente= Cliente(None, persona.id_persona, Some(Timestamp.valueOf(LocalDateTime.now())), None)
    cliente = clientes.insertOne(cliente)
    println("Ha guardado este cliente: " + cliente)
  }








}
