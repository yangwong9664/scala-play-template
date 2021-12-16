
name := "scala-play-template"
scalaVersion := "2.12.15"

val appDependencies = Seq(
  ws,
  guice
)

val testDependencies = Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % "test, it",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
  "de.leanovate.play-mockws" %% "play-mockws" % "2.8.1" % Test,
  "org.jsoup" % "jsoup" % "1.13.1" % Test,
  "com.github.tomakehurst" % "wiremock-standalone" % "2.27.2" % IntegrationTest
)

val loggingDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.8",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "com.lihaoyi" %% "sourcecode" % "0.2.7"
)

libraryDependencies ++= appDependencies ++ testDependencies ++ loggingDependencies

scalacOptions ++= Seq("-unchecked",
  "-deprecation",
  "-feature",
  "-encoding","UTF-8",
  "-explaintypes",
  "-language:higherKinds",
  "-Ypartial-unification",
  "-Ywarn-infer-any",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates"
)

dependencyUpdatesFilter -= moduleFilter(organization = "org.scala-lang")
dependencyUpdatesFilter -= moduleFilter(organization = "org.jsoup")

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(Defaults.itSettings,
    scalaSource in IntegrationTest := baseDirectory.value / "it",
    parallelExecution in IntegrationTest := false,
    parallelExecution in Test := false,
    fork in Test := true,
    fork in IntegrationTest := true,
    assemblyJarName in assembly := "scala-play-template.jar",
    coverageEnabled in (Test, compile) := true,
    coverageEnabled in (IntegrationTest, compile) := true,
    coverageEnabled in (Compile, compile) := false,
    coverageMinimumStmtTotal := 1,
    coverageFailOnMinimum := true,
    coverageHighlighting := true,
    coverageExcludedPackages := "<empty>;Reverse.*;view.*;.*(BuildInfo|Routes).*;",
    dependencyUpdatesFailBuild := true,
    dependencyUpdatesFilter -= moduleFilter(name = "twirl-api"),
    scalastyleFailOnError := true
  )
  .enablePlugins(PlayScala)
