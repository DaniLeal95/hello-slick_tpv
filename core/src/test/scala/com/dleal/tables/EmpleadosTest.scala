package com.dleal.tables

import java.sql.{Date, Timestamp}
import java.time.LocalDateTime

import com.dleal.caseClass.{Empleado, Persona}
import com.dleal.repositories.{EmpleadoRepositorio, PersonaRepositorio}
import com.dleal.util.DbConfiguration
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class EmpleadosTest  extends FunSuite with BeforeAndAfterAll  with ScalaFutures with DbConfiguration
{
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  System.setProperty("db_name", "db")

  System.setProperty("db_config", "src/test/resources/application.conf")

  val empleados = new EmpleadoRepositorio(config)
  val personas = new PersonaRepositorio(config)

  override protected def beforeAll(){
    println("BEFORE ALL")
    // let's use test configurations (should use the application.test.conf settings)
    Await.result(empleados.init(),Duration.Inf)
  }

  override protected def afterAll(){
    println("AFTER ALL")
    Await.result(empleados.drop(), Duration.Inf)
  }

  test("testInsert") {

    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    var empleado: Empleado = Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)

    assert(empleado.id_empleado.isDefined)
//    println(empleado)
  }

  test("testInsertVarious") {
    var bufferAuxEmployee: ListBuffer[Empleado] = ListBuffer()

    for(i <- 1 to 10){
      val person = personas.insertOne(Persona(Some(0), Some("Daniel"+i), Some("Leal"+i), Some("Reyes"+i), Some(new Date(System.currentTimeMillis())),
        Some("CA"+i), Some("95199"+i), None))

        bufferAuxEmployee += Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    }


    bufferAuxEmployee = empleados.insertVarious(bufferAuxEmployee.toList)

    bufferAuxEmployee.foreach( f => {
      assert(f.id_empleado.isDefined)
//      println(f)
    })

  }

  test("deleteOnCascadeTest") {
    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    var empleado: Empleado = Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)

    val x = personas.delete(person.id_persona.get)

    val empleado2 = empleados.selectOne(empleado.id_empleado.get)

    assert(empleado2.isEmpty)
    assert(x > 0)
  }

  test("basicDeleteTest"){
    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    var empleado: Empleado = Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)

    val x = empleados.delete(empleado.id_empleado.get)

    val empleado2 = empleados.selectOne(empleado.id_empleado.get)

    assert(empleado2.isEmpty)
    assert(x > 0)
  }

  test( "updateTest"){
    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    var person2 = Persona(Some(0), Some("Rafael"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person2 = personas.insertOne(person2)

    var empleado: Empleado = Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)


    assert(empleado.id_persona.isDefined)


    val x: Int = empleados.update(empleado.copy(id_persona = person2.id_persona))
    val empleado2 = empleados.selectOne(empleado.id_empleado.get).get

    assert(x > 0)
    assert(empleado.id_persona !== empleado2.id_persona)
    assert(empleado2.id_persona === person2.id_persona)
  }

  test("InsertFailureTest"){


    var empleado: Empleado = Empleado(None,Some(60),Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)
    assert(empleado.id_empleado.isEmpty)

  }

  test( "selectFailureTest"){
    var empleado: Option[Empleado] = empleados.selectOne(60)

    assert(empleado.isEmpty)
  }

}
