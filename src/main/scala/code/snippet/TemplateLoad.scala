package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.{SHtml, Templates}
import net.liftweb.http.js.JsCmds.{Noop, SetHtml}
import net.liftweb.http.js.JsCmd
import net.liftweb.util.CssSel

object TemplateLoad {

  def content : JsCmd =
    Templates("index" :: Nil).map(ns => SetHtml("inject", ns)) openOr Noop

  def render: CssSel = "button [onclick]" #> SHtml.ajaxInvoke(content _)

}
