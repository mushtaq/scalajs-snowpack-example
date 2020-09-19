import Libs._
import org.openqa.selenium.chrome.ChromeOptions
import org.scalajs.jsenv.selenium.SeleniumJSEnv

inThisBuild(
  Seq(
    scalaVersion := "2.13.3",
    version := "0.1.0-SNAPSHOT",
    organization := "com.github.tmtsoftware.msocket",
    organizationName := "ThoughtWorks",
    resolvers ++= Seq(
      Resolver.bintrayRepo("mausamy", "tmtyped"),
      Resolver.bintrayRepo("oyvindberg", "ScalablyTyped")
    ),
    scalafmtOnCompile := true,
    scalacOptions ++= Seq(
      "-encoding",
      "UTF-8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-Wconf:any:warning-verbose",
      "-Wdead-code",
      "-Xlint:_,-missing-interpolator",
      "-Xsource:3",
      "-Xcheckinit"
//      "-Xasync" does not work with Scala.js js yet
    )
  )
)

//************* example-service *****************************************************

lazy val `scalajs-snowpack-example` = project
  .in(file("."))
  .aggregate(example)

lazy val `example` = project
  .enablePlugins(ScalaJSPlugin)
  .settings(
    libraryDependencies ++= Seq(
      scalatest.value % Test,
      `scala-async`,
      ScalablyTyped.R.rxjs
    ),
    jsEnv := new SeleniumJSEnv(
      new ChromeOptions().setHeadless(true),
      new TestConfig(baseDirectory.value).seleniumConfig
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule).withSourceMap(false) },
    snowpackTestServerProcess := None,
    startSnowpackTestServer := {
      new TestConfig(baseDirectory.value).startSnowpackTestServer()
    },
    stopSnowpackTestServer := snowpackTestServerProcess.value.foreach(_.destroy())
  )

lazy val startSnowpackTestServer   = taskKey[Process]("start snowpack test server")
lazy val stopSnowpackTestServer    = taskKey[Unit]("stop snowpack test server")
lazy val snowpackTestServerProcess = taskKey[Option[Process]]("process handle of the test server")
