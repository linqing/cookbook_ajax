package code.comet

import net.liftweb.actor.LiftActor
import net.liftweb.common.SimpleActor
import net.liftweb.http.{CometActor, CometListener, ListenerManager, RenderOut}

class ChatClient extends CometActor with CometListener {

  def registerWith: SimpleActor[Any] = ChatServer

  private var msgs: Vector[String] = Vector()

  override def lowPriority: PartialFunction[Any, Unit] = {
    case xs: Vector[String] =>
      msgs = xs
      reRender()
  }

  def render: RenderOut = "li *" #> msgs

}


object ChatServer extends LiftActor with ListenerManager {

  private var msgs = Vector("Welcome")

  def createUpdate: Any = msgs

  override def lowPriority: PartialFunction[Any, Unit] = {
    case s: String =>
      msgs :+= s
      updateListeners()
  }

}