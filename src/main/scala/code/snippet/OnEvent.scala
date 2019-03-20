package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml

import scala.util.Random
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.util.CssSel

object OnEvent {

  def render: CssSel = {

    val x, y = Random.nextInt(10)
    val sum = x + y

    "p *" #> "What is %d + %d?".format(x,y) &
    "input [onchange]" #> SHtml.onEvent( answer =>
      if (answer == sum.toString) Alert("Correct!")
      else Alert("Try again")
     )
  }

}
