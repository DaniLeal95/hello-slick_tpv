name := "hello-slick"

mainClass in Compile := Some("HelloSlick")

libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.slick" %% "slick-testkit" % "3.1.1" % "test" ,
  "org.slf4j" % "slf4j-nop" % "1.7.10",
  //"com.h2database" % "h2" % "1.4.187",
  "mysql" % "mysql-connector-java" % "8.0.16",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
)

fork in run := true
