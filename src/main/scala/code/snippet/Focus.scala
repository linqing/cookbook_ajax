package code.snippet

import net.liftweb.http.js.JsCmds.FocusOnLoad
import net.liftweb.util.CssSel
import net.liftweb.util.Helpers._

class Focus {

  def render: CssSel = "name=username" #>
    FocusOnLoad(<input type="text"/>)

}
