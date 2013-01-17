package org.coreyoliver.kink {
  import net.liftweb.common._
  import net.liftweb.util._
  import Helpers._
  import net.liftweb.json._
  import Extraction._
  import JsonDSL._
  import dispatch._
  import org.rogach.scallop._
  import org.rogach.scallop.exceptions._

  class Conf(arguments: Array[String])
      extends LazyScallopConf(arguments) {
    val obtainCode = new Subcommand("obtain-code") {
      val clientId = trailArg[String](
        "client-id",
        "The client id.",
        required = true
      )
      val responseType = trailArg[String](
        "response-type",
        "The response type.",
        required = true
      )
      val redirectUri = trailArg[String](
        "redirect-uri",
        "The redirect uri.",
        required = true
      )
      val scope = trailArg[String](
        "scope",
        "The scope.",
        required = true
      )
      val url = trailArg[String](
        "url",
        "The url.",
        required = true
      )
    }
  }

  object Kink extends KinkImpl

  trait KinkImpl {

    protected def authorizeUrl(url: String, clientId: String, responseType: String, scope: String) {
      val oauth_url = host(url) <<?
      Map("client_id" -> clientId, "response_type" -> responseType, "scope" -> scope)

      oauth_url.secure.build.getRawUrl
    }
  }

  object Main extends App {
    val conf = new Conf(args)
    conf.initialize {
      case Version => Unit 
      case Exit() => Unit
      case ScallopException(message) => Unit
      case RequiredOptionNotFound(optionName) => Unit
    }

    println(conf.obtainCode)
  }
}
