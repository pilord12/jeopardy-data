name := "jeopardy-data"

version := "0.1"

lazy val scalatest = "org.scalatest" %% "scalatest" % "3.0.5" % "it,test"
lazy val scalajhttp = "org.scalaj" %% "scalaj-http" % "2.3.0"

lazy val commonSettings = Seq(
  scalaVersion := "2.12.7",
  libraryDependencies ++= Seq(
    scalatest,
    scalajhttp
  ),
  coverageExcludedPackages := ".*JeopardyMain.*"
)

lazy val root = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    commonSettings,
    Defaults.itSettings
  )
