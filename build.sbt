name := """activator-akka-spray"""

version := "1.0"

scalaVersion := "2.10.2"

resolvers += "spray repo" at "http://repo.spray.io"

resolvers += "spray nightlies" at "http://nightlies.spray.io"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-can" % "1.3.1",
  "io.spray" %% "spray-routing" % "1.3.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.5"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")