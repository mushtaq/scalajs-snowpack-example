package example

import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers

class JsTest extends AsyncFreeSpec with Matchers {

  "demo" in {
    Main.printEachAndCollect(50)
    println("**********************")
    println("**********************")
    1 shouldBe 1
  }

}
