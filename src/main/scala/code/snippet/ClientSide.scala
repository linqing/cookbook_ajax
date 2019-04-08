package code.snippet

import net.liftweb.http.js.JE
import net.liftweb.http.js.JsCmds.{Alert, RedirectTo}
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

object ClientSide {

  def render: CssSel = "button [onclick]" #> "$(this).fadeOut()"

  def combined: CssSel =
    "button [onclick]" #> (Alert("Here we go...") & RedirectTo("http://liftweb.net"))

  def callgreet: CssSel =
    "button [onclick]" #> JE.Call("greet", "World!", 3)

}
