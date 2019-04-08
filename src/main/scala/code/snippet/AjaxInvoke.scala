package code.snippet

import net.liftweb.common.Loggable
import net.liftweb.http.SHtml
import net.liftweb.http.js.{JsCmd, JsCmds}
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

object AjaxInvoke extends Loggable {

  def callback(): JsCmd = {
    logger.info("The button was pressed")
    JsCmds.Alert("You clicked it")
  }

  def button: CssSel = "button [onclick]" #> SHtml.ajaxInvoke(callback)
}
