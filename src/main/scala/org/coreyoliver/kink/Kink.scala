package org.coreyoliver.kink {
  import org.rogach.scallop._
  import org.rogach.scallop.exceptions._

  class Conf(
    arguments: Array[String],
    onError:   (Throwable, Scallop) => Nothing
  ) extends ScallopConf(arguments) {
    val obtainCode = new Subcommand("obtain-code") {
      val clientId = opt[String](
        "client-id",
        'c',
        "The client id.",
        required = true,
        argName = "client-id"
      )
      val responseType = opt[String](
        "response-type",
        'r',
        "The response type.",
        required = true,
        argName = "response-type"
      )
      val redirectUri = opt[String](
        "redirect-uri",
        'u',
        "The redirect uri.",
        required = true,
        argName = "redirect-uri"
      )
      val scope = opt[String](
        "scope",
        's',
        "The scope.",
        required = true,
        argName = "scope"
      )
    }
    override protected def onError(e: Throwable) = onError(e, builder)
  }

  object Main extends App {
    val conf = new Conf(args, onError)
    println("client-id: %s".format(conf.obtainCode.clientId))


    private def onError(e: Throwable, scallop: Scallop) = e match {
      case Help(_) =>
        scallop.printHelp
        sys.exit(0)
      case _ =>
        println("Error: %s".format(e.getMessage))
        scallop.printHelp
        sys.exit(1)
    }
  }
}
