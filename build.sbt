name := "hello-slick"

mainClass in Compile := Some("HelloSlick")

lazy val global = project
  .in(file("."))
  .aggregate(
    core,
    business,
    view
  )

lazy val core = project
  .settings(
    name := "core",
    libraryDependencies ++= commonDependencies
  )


lazy val business = project
  .settings(
    name := "business",
    libraryDependencies ++= commonDependencies
  )
  .dependsOn(
    core
  )

lazy val view = project
  .settings(
    name := "view",
    libraryDependencies ++= commonDependencies
  )
  .dependsOn(
    business
  )

lazy val commonDependencies = Seq(
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.slick" %% "slick-testkit" % "3.1.1" % "test" ,
  "org.slf4j" % "slf4j-nop" % "1.7.10",
  "com.h2database" % "h2" % "1.4.187",
  "mysql" % "mysql-connector-java" % "8.0.16",
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.0-M2",
  "com.typesafe.slick" %% "slick-codegen" % "3.0.0"
)

fork in run := true

