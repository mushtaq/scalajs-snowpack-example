addSbtPlugin("com.timushev.sbt" % "sbt-updates"  % "0.5.1")
addSbtPlugin("org.scalameta"    % "sbt-scalafmt" % "2.4.2")
addSbtPlugin("org.scala-js"     % "sbt-scalajs"  % "1.2.0")

libraryDependencies += "org.scala-js" %% "scalajs-env-selenium" % "1.0.1-SNAPSHOT"

scalacOptions ++= Seq(
  "-encoding",
  "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint:-unused,_",
  "-Ywarn-dead-code",
  "-Xfuture"
)

resolvers += Resolver.bintrayIvyRepo("rtimush", "sbt-plugin-snapshots")
addSbtPlugin("com.timushev.sbt" % "sbt-rewarn" % "0.0.1-15-3102b36")

resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped")
addSbtPlugin("org.scalablytyped" % "sbt-scalablytyped" % "202008250800")
