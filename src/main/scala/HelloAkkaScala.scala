//Import akka actors and spray
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.io.IO
import spray.can.Http
import spray.http.{HttpMethods, HttpRequest, HttpResponse, Uri}

class App extends Actor {

  implicit val system = context.system
    //add a listener on localhost and port 8080
  override def receive = {
    case "start" =>
      val listener: ActorRef = system.actorOf(Props[HttpListener])
      IO(Http) ! Http.Bind(listener, interface = "localhost", port = 8080)
  }

}

class HttpListener extends Actor {
    //using spray we can access http requests so if we have /ping on our url pong will be requested and showed
  def receive = {
    case _: Http.Connected =>
      sender() ! Http.Register(self)
    case HttpRequest(HttpMethods.GET, Uri.Path("/ping"), _, _, _) =>
  sender ! HttpResponse(entity = "PONG")
  }

}

object Main {

  def main(args: Array[String]) {
    val system = ActorSystem("my-actor-system")
    val app: ActorRef = system.actorOf(Props[App], "app")
    app ! "start"
  }

}