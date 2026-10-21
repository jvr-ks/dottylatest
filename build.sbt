// build.sbt

lazy val root = project
  .in(file("."))
  .settings(
    name := "Dottylatest",
    //scalaVersion := sys.env.getOrElse("DOTTYLATEST", "3.9.0"),
    scalaVersion := "3.9.0",
    version := "",
    organization := "de.jvr",
    logLevel := Level.Warn,
    Compile / mainClass := Some("de.jvr.dottylatest.Dottylatest"),
    Compile / packageBin / artifactPath :=
      fileConverter.value.toVirtualFile(
        (target.value / "dottylatest.jar").toPath
      ),
    libraryDependencies ++= Seq.empty,
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature"
    )
  )
