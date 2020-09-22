package example

import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers

import scala.async.Async._
import scala.concurrent.ExecutionContext

class JsTest extends AsyncFreeSpec with Matchers {

  override implicit def executionContext: ExecutionContext = ExecutionContext.global

  "demo" in async {
    println("running test *********************")
    await(Main.printEachAndCollect(5)) shouldBe (0 to 4)
  }

}
