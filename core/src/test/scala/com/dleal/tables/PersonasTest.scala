package com.dleal.tables

import java.sql.Date

import com.dleal.caseClass.Persona
import com.dleal.repositories.PersonaRepositorio
import com.dleal.util.{DbConfiguration, ParametersName}
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration


class PersonasTest extends FunSuite with BeforeAndAfterAll  with ScalaFutures with DbConfiguration{

  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  System.setProperty("db_name", ParametersName.testConfDb)
  System.setProperty("db_config", ParametersName.testConfFile)

  val personas = new PersonaRepositorio(config)

  override protected def beforeAll(){

    println("BEFORE ALL")
    // let's use test configurations (should use the application.test.conf settings)
    Await.result(personas.init(),Duration.Inf)
  }

  override protected def afterAll(){
    println("AFTER ALL")
    personas.drop()
  }

  test("testInsertOne") {
    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    assert(person.id_persona.isDefined)
    assert(person.id_persona.get > 0)
//    println(person)
  }

  test("testInsertVarious") {

    var listBuffer: ListBuffer[Persona] = ListBuffer()
    for(i <- 1 to 10){
      listBuffer += Persona(Some(0), Some("PEPE"+i), Some("PINRELE"+i), None, Some(new Date(System.currentTimeMillis())),
        Some("CA"), Some(i.toString), None)
    }

    listBuffer = personas.insertMultiple(listBuffer)

    listBuffer.foreach(x => {
      assert(x.id_persona.isDefined)
      assert(x.id_persona.get > 0)
    })

  }

  test("testSelectAll") {
    val allTable : ListBuffer[Persona] = personas.selectAll()

    assert(allTable.size > 0)

//    allTable.foreach(println)
  }

  test("testSelectOne") {
    val person : Option[Persona] = personas.selectOne(1)


    assert(person.isDefined)
    assert(person.get.nombre.getOrElse("").equals("Daniel"))
//    println(person)


  }

  test("testFind") {
    val persona = personas.find(Persona(None, Some("Daniel"),None, None,None,
      None, None, None))

    assert(persona.size == 1)

    val personasList = personas.find(Persona(None, None, None,None,None,
      Some("CA"),None, None))

    assert(personasList.size > 1)
  }


  test("testUpdate") {
    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)

    person = personas.insertOne(person)

    val person2 = person.copy(nombre = Some("SRAISRI"))

    val x = personas.update(person2)

    person = personas.selectOne(person2.id_persona.get).get

    assert(person.nombre == person2.nombre)
    assert(x > 0)
  }

  test( "testDelete"){

    var person = Persona(Some(0), Some("Daniel"), Some("Leal"), Some("Reyes"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)
    person = personas.insertOne(person)

    val x = personas.delete(person.id_persona.get)

    assert( x > 0) // 0 Failure , >0 Success

    val get = personas.selectOne(person.id_persona.get)

    assert(get.isEmpty)



  }




}
