package com.dleal.util

import java.io.File

import com.typesafe.config.ConfigFactory
import slick.backend.DatabaseConfig
import slick.driver.JdbcProfile

trait DbConfiguration {

//  lazy val config = DatabaseConfig.forConfig[JdbcProfile]("db")
  lazy val config = DatabaseConfig.forConfig[JdbcProfile](System.getProperty("db_name"),ConfigFactory.parseFile(new File(System.getProperty("db_config"))))

}
