package com.dleal.tables

import java.sql.Date

import com.dleal.caseClass.Persona
import org.scalatest.FunSuite

import scala.collection.mutable.ListBuffer

class PersonasTest extends FunSuite {

  test("testInsertOne") {

    val t: slick.lifted.Tag = null
    val personasTable = new Personas(t)

    var person = Persona(Some(0), Some("Dylan"), Some("ES CARAJOTE"), Some("ESA"), Some(new Date(System.currentTimeMillis())),
      Some("CA"), Some("95199"), None)


    person = personasTable.insertOne(person)

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
