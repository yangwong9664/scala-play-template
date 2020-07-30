
name := "yang-frontend-template"

scalaVersion := "2.12.8"

scalaSource in IntegrationTest := baseDirectory.value / "it"

parallelExecution in IntegrationTest := false
parallelExecution in Test := false

fork in Test := true
fork in IntegrationTest := true

libraryDependencies ++= {
  Seq(
    ws,
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % "test, it",
    "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
    "de.leanovate.play-mockws" %% "play-mockws" % "2.6.6" % Test,
    "org.jsoup" % "jsoup" % "1.11.3" % Test,
    "com.github.tomakehurst" % "wiremock-standalone" % "2.19.0" % IntegrationTest
  )
}

dependencyUpdatesFailBuild := true
dependencyUpdatesFilter -= moduleFilter(name = "twirl-api")

scalastyleFailOnError := true

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature", "-Xfatal-warnings")

coverageEnabled in (Test, compile) := true
coverageEnabled in (IntegrationTest, compile) := true
coverageEnabled in (Compile, compile) := false
coverageMinimum := 1
coverageFailOnMinimum := true
coverageHighlighting := true
coverageExcludedPackages := "<empty>;Reverse.*;view.*;.*(BuildInfo|Routes).*;"

lazy val root = (project in file(".")).enablePlugins(PlayScala).configs(IntegrationTest).settings(Defaults.itSettings: _*)

assemblyJarName in assembly := "yang-frontend-template.jar"
