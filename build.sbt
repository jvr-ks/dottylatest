// build.sbt

lazy val root = (project in file("."))
.settings(
  name := "Dottylatest",
  ThisBuild / scalaVersion := "3.8.1",
//  ThisBuild / scalaVersion := sys.env.get("DOTTYLATEST").getOrElse("3.8.1")
  version := "",
  organization := "de.jvr",
  logLevel := Level.Warn,
  Compile / mainClass := Some("de.jvr.dottylatest.Dottylatest"),
  Compile / packageBin / artifactPath := baseDirectory.value / "dottylatest.jar",
  libraryDependencies ++= Seq(
  ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature"
    ),
)


  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  