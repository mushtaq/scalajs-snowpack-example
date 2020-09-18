import Libs._
import org.openqa.selenium.chrome.ChromeOptions
import org.scalajs.jsenv.Input
import org.scalajs.jsenv.selenium.SeleniumJSEnv
import sbt.Keys.artifactPath

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
    // SeleniumJSENV does not support ESModules for now
    jsEnv in Test := new SeleniumJSEnv(
      new ChromeOptions().setHeadless(true),
      SeleniumJSEnv
        .Config()
        .withMaterializeInServer("test", "http://localhost:8080/test/")
    ),
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.ESModule).withSourceMap(false) },
    // testHtml does not work even with Scala.js 1.2.0 if you import modules
    Test / testHtml / artifactPath := (Test / scalaJSTestHTMLArtifactDirectory).value / "test.html"
//    Test / jsEnvInput := Seq(Input.ESModule())
  )
