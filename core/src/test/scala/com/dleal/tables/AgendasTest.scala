package com.dleal.tables

import java.sql.{Date, Timestamp}
import java.text.{DateFormat, SimpleDateFormat}
import java.time.LocalDateTime
import java.util.TimeZone

import com.dleal.caseClass.{Agenda, Empleado, Persona}
import com.dleal.repositories.{AgendaRepositorio, EmpleadoRepositorio, PersonaRepositorio}
import com.dleal.util.{DbConfiguration, ParametersName}
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class AgendasTest extends FunSuite with BeforeAndAfterAll  with ScalaFutures with DbConfiguration
{
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  System.setProperty("db_name", ParametersName.testConfDb)
  System.setProperty("db_config", ParametersName.testConfFile)

  val empleados = new EmpleadoRepositorio(config)
  val personas = new PersonaRepositorio(config)
  val agendas = new AgendaRepositorio(config)

  def getTimeStamp(timeStr: String): Timestamp = {

    val dateFormat: DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))

    val date: Option[Timestamp] = {
      try {
        Some(new Timestamp(dateFormat.parse(timeStr).getTime))
      } catch {
        case _: Exception => Some(Timestamp.valueOf("19700101'T'000000"))
      }
    }

    date.getOrElse(Timestamp.valueOf(timeStr))
  }

  override protected def beforeAll(){
    println("BEFORE ALL")
    // let's use test configurations (should use the application.test.conf settings)
    agendas.init()
  }

  override protected def afterAll(){
    println("AFTER ALL")
    Await.result(agendas.drop(), Duration.Inf)
  }

  test("testInsertOne") {

    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    var empleado: Empleado = Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)

    var agenda: Agenda = Agenda(None,empleado.id_empleado,Some(getTimeStamp("2019-11-20 17:00:00")),Some(getTimeStamp("2019-11-20 17:30:00")),Some('A'),
      Some("Pelar a Menganito"),None,Some(Timestamp.valueOf(LocalDateTime.now())))

    agenda = agendas.insertOne(agenda)

    assert(agenda.id_tarea.isDefined)
//    println(agenda)
  }

  test("testInsertVarious") {

    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    var empleado: Empleado = Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)



    var bufferAuxWorks: ListBuffer[Agenda] = ListBuffer()

    for(i <- 5 to 9){
      bufferAuxWorks += Agenda(None,empleado.id_empleado,Some(getTimeStamp("2019-11-20 1"+ i +":00:00")),Some(getTimeStamp("2019-11-20 1" + i + ":30:00")),Some('A'),
        Some("Pelar a Menganito"+i ),None,Some(Timestamp.valueOf(LocalDateTime.now())))
    }

    bufferAuxWorks = agendas.insertVarious(bufferAuxWorks.toList)

    bufferAuxWorks.foreach(  agenda => assert(agenda.id_tarea.isDefined))

  }


  test ("testSelectById") {
    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    var empleado: Empleado = Empleado(None,person.id_persona,Some(Timestamp.valueOf(LocalDateTime.now())),None)
    empleado = empleados.insertOne(empleado)

    var agenda: Agenda = Agenda(None,empleado.id_empleado,Some(getTimeStamp("2019-11-20 17:00:00")),Some(getTimeStamp("2019-11-20 17:30:00")),Some('A'),
      Some("Pelar a Menganito"),None,Some(Timestamp.valueOf(LocalDateTime.now())))

    agenda = agendas.insertOne(agenda)

    val agenda2: Option[Agenda] = agendas.selectOne(agenda.id_tarea.get)

    assert(agenda2.isDefined)
    assert(agenda.fecha_creacion === agenda2.get.fecha_creacion)
  }



}

