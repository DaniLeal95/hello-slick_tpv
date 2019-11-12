package com.dleal.tables

import java.sql.Date

import com.dleal.caseClass.Persona
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import slick.driver.MySQLDriver
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import slick.driver.H2Driver.api._
import slick.jdbc.meta._

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration


class PersonasTest extends FunSuite with BeforeAndAfterAll  with ScalaFutures{
  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))
  var db: Database = _
  val personTable = TableQuery[Personas]


  override protected def beforeAll(){

    println("BEFORE ALL")
    // let's use test configurations (should use the application.test.conf settings)
    db = Database.forConfig("Dbmemory")
    db.createSession()
    db.run(personTable.schema.create).futureValue
    System.setProperty("db_name", "Dbmemory")





  }

  override protected def afterAll(){
    println("AFTER ALL")
    db.close()
  }


  def insertOne(person: Persona, single: Boolean = true): Persona = {
    val _ec = Some(db.executor.executionContext)

    try {
      val personas: TableQuery[Personas] = TableQuery[Personas]
      val insertQuery = personas returning personas.map(_._id_persona) into ((item, id) => item.copy(id_persona = Some(id)))
      val action = insertQuery += person
      val personReturn: Persona = Await.result(db.run(action).map {
        _ match {
          case x: Persona => x
          case _ => Persona.apply(None, None, None, None, None, None, None, None)
        }
      }(_ec.get).recover {
        case e: java.sql.SQLException => {
          println(e)
          Persona.apply(None, None, None, None, None, None, None, None)
        }
      }(_ec.get), Duration.Inf)

      personReturn
    }
    catch {
      case e: Exception => {
        println(e)
        Persona.apply(None, None, None, None, None, None, None, None)
      }
    }

  }

  test("testInsertOne") {

    val t: slick.lifted.Tag = null
    val personasTable = new Personas(t)

    var person = Persona(Some(0), Some("Dylan"), Some("ES CARAJOTE"), Some("ESA"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)


    person = insertOne(person)

    assert(person.id_persona.isDefined)
    assert(person.id_persona.get > 0)
  }

  test("testInsertVarious") {

    val t: slick.lifted.Tag = null
    val personasTable = new Personas(t)

    var listBuffer: ListBuffer[Persona] = ListBuffer()
    for(i <- 1 to 10){
      listBuffer += Persona(Some(0), Some("PEPE"+i), Some("PINRELE"+i), None, Some(new Date(System.currentTimeMillis())),
        Some("CA"), Some(i.toString), None)
    }

    listBuffer = personasTable.insertMultiple(listBuffer)

    listBuffer.foreach(x => {
      assert(x.id_persona.isDefined)
      assert(x.id_persona.get > 0)
    })

  }

  test("testSelectAll") {

    val t: slick.lifted.Tag = null
    val personasTable = new Personas(t)

    val allTable : ListBuffer[Persona] = personasTable.selectAll()


    assert(allTable.size > 0)

    allTable.foreach(println)
  }

  test("testSelectOne") {

    val t: slick.lifted.Tag = null
    val personasTable = new Personas(t)

    val person : Option[Persona] = personasTable.selectOne(1)


    assert(person.isDefined)
    assert(person.get.nombre.getOrElse("").equals("Daniel"))
    println(person)


  }

  test("testFind") {
    val t: slick.lifted.Tag = null
    val personasTable = new Personas(t)

    val persona = personasTable.find(Persona(None, Some("Dylan"), Some("ES CARAJOTE"), Some("ESA"),None,
      Some("CA"), Some("95199"), None))

    assert(persona.size == 1)

    val personas = personasTable.find(Persona(None, None, None,None,None,
      Some("CA"),None, None))

    assert(personas.size > 1)
  }


}
