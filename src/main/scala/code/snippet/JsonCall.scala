package code.snippet

import net.liftweb.common.Loggable
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.http.js.{JE, JsCmd}
import net.liftweb.json.JsonAST._
import net.liftweb.json.{DefaultFormats, Formats}
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

object JsonCall extends Loggable {

  implicit val formats: Formats = DefaultFormats

  case class Question(first: Int, second: Int, answer: Int) {
    def valid_? : Boolean = first + second == answer
  }

  def render: CssSel = {

    def validate(value: JValue): JsCmd = {
      logger.info(value)
      value.extractOpt[Question].map(_.valid_?) match {
        case Some(true) => Alert("Looks good")
        case Some(false) => Alert("That doesn't add up")
        case None => Alert("That doesn't make sense")
      }
    }

    "button [onclick]" #>
      SHtml.jsonCall(JE.Call("currentQuestion"), validate)
  }
}
