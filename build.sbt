ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.19"

val jvmArgs = Seq(
    "--add-opens=java.base/java.lang=ALL-UNNAMED",
    "--add-opens=java.base/java.util.concurrent=ALL-UNNAMED",
    "--add-opens=java.base/java.math=ALL-UNNAMED",
    "--add-opens=java.base/java.util=ALL-UNNAMED",
    "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED",
    "--add-opens=java.base/java.lang.reflect=ALL-UNNAMED",
    "--add-opens=java.base/java.io=ALL-UNNAMED",
    "--add-opens=java.base/java.net=ALL-UNNAMED",
    "--add-opens=java.base/java.nio=ALL-UNNAMED",
    "--add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED",
    "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED",
    "--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",
    "--add-opens=java.base/sun.security.action=ALL-UNNAMED",
    "--add-opens=java.base/sun.util.calendar=ALL-UNNAMED",
    "--add-opens=java.security.jgss/sun.security.krb5=ALL-UNNAMED",
    //  "--add-opens=java.lang/java.lang=ALL-UNNAMED",
)

lazy val root = (project in file("."))
    .settings(
        name := "spark-insert-into",
        Test / fork := true,
        Test / javaOptions ++= jvmArgs,
        libraryDependencies += "org.apache.spark" %% "spark-core" % "3.5.1",
        libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.1",
        libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
        libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test,
    )




// javaOptions in Runtime ++= jvmArgs