import java.io.File
import java.lang.ProcessBuilder.Redirect
import java.nio.file.Files

import org.scalajs.jsenv.selenium.SeleniumJSEnv

class SnowpackTestConfig(baseDir: File) {
  private val contentDirName = "test-run"
  private val contentDir     = s"$baseDir/target/$contentDirName"
  private val testPort       = 9091
  private val webRoot        = s"http://localhost:$testPort/"

  private val snowpackTestConfig: String =
    s"""
       |{
       |  "mount": {
       |    "$contentDir" : "/"
       |  },
       |  "devOptions": {
       |    "port": $testPort,
       |    "open": "none",
       |    "hmr": false
       |  }
       |}
       |""".stripMargin

  def start(): Process = {
    val testConfigPath = Files.createTempFile(null, ".json")
    Files.write(testConfigPath, snowpackTestConfig.getBytes())
    testConfigPath.toFile.deleteOnExit()

    val processBuilder = new ProcessBuilder("npm", "start", "--", "--config", testConfigPath.toString)
      .directory(baseDir)
      .redirectError(Redirect.INHERIT)

    processBuilder.start()
  }

  def seleniumConfig: SeleniumJSEnv.Config = {
    SeleniumJSEnv
      .Config()
      .withMaterializeInServer(contentDir, webRoot)
  }
}
