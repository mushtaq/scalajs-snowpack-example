package example

import typings.rxjs.{mod => rxjs, rxjsMod => ops}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Main {

  def main(args: Array[String]): Unit = {
    printEachAndCollect(5).onComplete(println)
  }

  def printEachAndCollect(n: Int): Future[Double] = {
    val observable = rxjs.interval(1000).pipe(ops.take(10))
    observable.subscribe(x => println(x))
    val result     = observable.pipe(ops.take(n), ops.toArray[Double]())
    result.toPromise[Double]().toFuture
  }

}
