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
    jsEnv in Test := new SeleniumJSEnv(
      new ChromeOptions().setHeadless(true),
      new SnowpackTestConfig(baseDirectory.value).seleniumConfig
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule).withSourceMap(false) },
    Test / test := {
      val process = Def.task {
        new SnowpackTestConfig(baseDirectory.value).start()
      }.value
      val _       = (Test / test).value
      process.destroy()
    },
    Test / testHtml := {
      val process      = Def.task {
        new SnowpackTestConfig(baseDirectory.value).start()
      }.value
      val testHtmlFile = (Test / testHtml).value
      process.destroy()
      testHtmlFile
    }
  )
