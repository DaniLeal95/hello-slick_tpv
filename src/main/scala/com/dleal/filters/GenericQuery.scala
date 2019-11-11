package com.dleal.filters

import com.dleal.caseClass.Persona
import slick.ast.Filter
import slick.driver.MySQLDriver
import slick.lifted.TableQuery
import slick.driver.MySQLDriver.api._
import slick.jdbc.GetResult
import slick.model.Table

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext}

object GenericQuery {

  def generateQuery(obj: Any, tableObject: TableQuery[_], tableName: String)
                   (implicit db:MySQLDriver.backend.Database, getResult: GetResult[Any],ec:ExecutionContext) = {

    val listOption : ListBuffer[Option[Rep[Boolean]]] = ListBuffer()
    var property: Option[_] = None

    val result = /*Await.result(db.run(*/tableObject.filter{

      x => {



        obj.getClass.getDeclaredFields.foreach(prop => {

          prop.setAccessible(true)

          property = prop.get(obj).asInstanceOf[Option[_]]



          listOption += property.map(z => {
            val proper = x.getClass.getDeclaredField("_"+prop.getName)
            proper.setAccessible(true)
            println(proper.get(x))
            val value = proper.get(x)
            proper.setAccessible(false)
            value == z
          })


          prop.setAccessible(false)
        })

        listOption.toList.collect({case Some(criteria)  => criteria}).reduceLeftOption(_ || _).getOrElse(true: Rep[Boolean])
      }

    }.result/*),Duration.Inf)*/

    result
  }

//    val query : StringBuilder = new StringBuilder()
//    val campos: StringBuilder = new StringBuilder()
/*
    for ( prop  <-tableObject.getClass.getDeclaredFields ){

      prop.setAccessible(true)
      val property = prop.get(obj).asInstanceOf[Option[_]]

      if(property.isDefined) {
        query.append(prop.getName).append(" = '").append(property.get.toString).append("'")
      }
      campos.append(prop.getName).append(",")
      prop.setAccessible(false)
    }

    tableObject.filter( x => {


    })*/

/*    for ( prop  <- obj.getClass.getDeclaredFields ){

      prop.setAccessible(true)
      val property = prop.get(obj).asInstanceOf[Option[_]]
      if(property.isDefined) {
        query.append(" and ").append(prop.getName).append(" = '").append(property.get.toString).append("'")
      }
      campos.append(prop.getName).append(",")
      prop.setAccessible(false)
    }*/

//    campos.deleteCharAt(campos.size -1 ).toString()
//    val squery: String = s"select $campos from $tableName ;" //where ${query.toString()};"

//    println(squery)
//    val action = sql"""$squery""".as[Any]
//    val result: Vector[Any] = Await.result(db.run(action), Duration.Inf)

//    result.foreach(_.toString )

}
