package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.js.JsCmds.FocusOnLoad
import net.liftweb.util.CssSel

class Focus {

  def render: CssSel = "name=username" #>
    FocusOnLoad(<input type="text"/>)

}
