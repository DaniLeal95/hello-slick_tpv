package com.dleal.filters

import slick.lifted.{CanBeQueryCondition, Query}
//import scala.slick.lifted.CanBeQueryCondition

/*

case class MaybeFilter[X, Y](val query: scala.slick.lifted.Query[X, Y]) {
  def filter[T,R:CanBeQueryCondition](data: Option[T])(f: T => X => R) = {
    data.map(v => MaybeFilter(query.filter(f(v)))).getOrElse(this)
  }
}*/
