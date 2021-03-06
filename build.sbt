val sharedSettings = Seq(
  version := scalatex.SbtPlugin.scalatexVersion,
  organization := "com.lihaoyi",
  crossScalaVersions:= Seq("2.10.4", "2.11.2"),
  scalaVersion := "2.11.4",
  libraryDependencies += "com.lihaoyi" %% "acyclic" % "0.1.2" % "provided",
  addCompilerPlugin("com.lihaoyi" %% "acyclic" % "0.1.2"),
  autoCompilerPlugins := true
)

lazy val scalaParser = project.settings(sharedSettings:_*)
  .settings(
    name := "scala-parser-lite",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "utest" % "0.2.4",
      "org.parboiled" %% "parboiled" % "2.0.1"
    ),
    testFrameworks += new TestFramework("utest.runner.JvmFramework")
  )
lazy val api = project.settings(sharedSettings:_*)
  .dependsOn(scalaParser)
  .settings(
    name := "scalatex-api",
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "utest" % "0.2.4",
      "com.scalatags" %% "scalatags" % "0.4.2",
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.parboiled" %% "parboiled" % "2.0.1"
    ),
    testFrameworks += new TestFramework("utest.runner.JvmFramework")
  )

lazy val scalatexSbtPlugin = project.settings(sharedSettings:_*)
  .settings(
  name := "scalatex-sbt-plugin",
  scalaVersion := "2.10.4",
  sbtPlugin := true
)

lazy val readme = project.settings(scalatex.SbtPlugin.projectSettings:_*).dependsOn(api).settings(
  libraryDependencies := libraryDependencies.value.filter(!_.toString.contains("scalatex-api")),
  scalaVersion := "2.11.4"
)