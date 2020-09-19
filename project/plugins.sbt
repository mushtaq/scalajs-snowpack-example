addSbtPlugin("com.timushev.sbt" % "sbt-updates"  % "0.5.1")
addSbtPlugin("org.scalameta"    % "sbt-scalafmt" % "2.4.2")
addSbtPlugin("org.scala-js"     % "sbt-scalajs"  % "1.2.0")
addSbtPlugin("com.timushev.sbt" % "sbt-rewarn"   % "0.1.1")

resolvers += "jitpack" at "https://jitpack.io"
libraryDependencies += "com.github.mushtaq.scala-js-env-selenium" %% "scalajs-env-selenium" % "1a06087"

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

resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped")
addSbtPlugin("org.scalablytyped" % "sbt-scalablytyped" % "202008250800")
