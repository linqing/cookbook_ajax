package code.snippet

import net.liftweb.http.SHtml
import code.comet.ChatServer
import net.liftweb.http.js.JsCmds.SetValById

import scala.xml.NodeSeq

object ChatSnippet {

  def render: NodeSeq => NodeSeq = SHtml.onSubmit(s => {
    ChatServer ! s
    SetValById("message", "")
  })
}
