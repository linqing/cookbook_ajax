package bootstrap.liftweb

import net.liftweb._

import common._
import http._
import sitemap._
import Loc._
import net.liftmodules.JQueryModule
import net.liftweb.http.js.jquery._

import net.liftweb.http.js.JsCmds.Run


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index", // the simple way to declare a menu

      Menu.i("Ajax Invoke") / "ajaxinvoke",
      Menu.i("OnEvent") / "onevent",
      Menu.i("AjaxCall") / "ajaxCall",
      Menu.i("JsonCall") / "jsonCall",
      Menu.i("HTML Select") / "htmlselect",
      Menu.i("Html Select and Form") / "htmlselectform",
      Menu.i("Client-Side Action") / "clientside",
      Menu.i("Focus On Load") / "focusonload",
      Menu.i("CSS Class on Ajax Form") / "formcss",
      Menu.i("Dynamic Template Load") / "templateload",
      Menu.i("JavaScript in Tail") / "jstail",
      Menu.i("Chat with Comet Failure Detection") / "chat",
      Menu.i("Ajax File Upload") / "ajaxfileupload",
      Menu.i("Wiring") / "wiring",


      // more complex because this menu allows anything in the
      // /static path to be visible
      Menu(Loc("Static", Link(List("static"), true, "/static/index"),
        "Static Content")))

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries:_*))

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))

    //Init the jQuery module, see http://liftweb.net/jquery for more information.
    LiftRules.jsArtifacts = JQueryArtifacts
    JQueryModule.InitParam.JQuery=JQueryModule.JQuery172
    JQueryModule.init()

    // Customize Comet session failure detection:
    LiftRules.noCometSessionCmd.default.set( () => Run("stash()") )

    /* To simulate file upload max size failure:

      LiftRules.maxMimeFileSize = 100

      // For this to work you need servlet API as a dependency in build.sbt (which it is for this project)
      LiftRules.exceptionHandler.prepend {
        case (_, _, x : FileUploadIOException) => ResponseWithReason(BadResponse(), "Unable to process file. Too large?")
      }
      */

    LiftRules.dispatch.append(code.rest.AjaxFileUpload)


    LiftRules.securityRules = () => new SecurityRules(content = None)
  }
}
