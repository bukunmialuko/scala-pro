// def measureTime(f: => Unit) = {
//   val start = System.currentTimeMillis()
//   f
//   val end = System.currentTimeMillis()
//   println("Evaluation took " + (end - start) + " milliseconds")
// }

// measureTime(new Array[String](10 * 1000 * 1000).hashCode())

// measureTime { new Array[String](100 * 1000 * 1000).hashCode() }

// def retry[T](max: Int)(f: => T): T = {
//   var tries = 0
//   var result: Option[T] = None
//   while (result == None) {
//     try { result = Some(f) }
//     catch {
//       case e: Throwable =>
//         tries += 1
//         if (tries > max) throw e
//         else {
//           println(s"failed, retry #$tries")
//         }
//     }
//   }
//   result.get
// }

// val httpbin = "https://httpbin.org";

// retry(max = 5) {
//   // Only succeeds with a 200 response
//   // code 1/3 of the time
//   requests.get(
//     s"$httpbin/status/200,400,500"
//   )
// }

trait StrParser[T] { def parse(s: String): T }
object StrParser {
  implicit object ParseInt extends StrParser[Int] {
    def parse(s: String) = s.toInt
  }
  implicit object ParseBoolean extends StrParser[Boolean] {
    def parse(s: String) = s.toBoolean
  }
  implicit object ParseDouble extends StrParser[Double] {
    def parse(s: String) = s.toDouble
  }
}

def parseFromString[T](s: String)(implicit parser: StrParser[T]) = {
  parser.parse(s)
}
val args = Seq("123", "true", "7.5")
val myInt = parseFromString[Int](args(0))
val myBoolean = parseFromString[Boolean](args(1))
val myDouble = parseFromString[Double](args(2))
