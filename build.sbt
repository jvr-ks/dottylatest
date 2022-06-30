// build.sbt

// fixed jar-name: "dottylatest.jar" in baseDirectory

lazy val root = (project in file("."))
.settings(
  name := "Dottylatest",
  ThisBuild / scalaVersion := sys.env.get("DOTTYLATEST").getOrElse("3.1.3"),
  version := "",
  organization := "de.jvr",
  logLevel := Level.Warn,
  Compile / packageBin / artifactPath := baseDirectory.value / "dottylatest.jar",
  libraryDependencies ++= Seq(
  ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-language:_"
    )
  
)



  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  