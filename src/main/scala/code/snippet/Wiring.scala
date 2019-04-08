package code.snippet

import java.text.NumberFormat
import java.util.Locale

import net.liftweb.http.SHtml.ajaxInvoke
import net.liftweb.http.js.JsCmd
import net.liftweb.http.{S, WiringUI}
import net.liftweb.util.Helpers._
import net.liftweb.util.{Cell, CssSel, ValueCell}

import scala.xml.{NodeSeq, Text}

class Wiring {

  val cost = ValueCell(1.99)
  val quantity = ValueCell(1)
  val subtotal: Cell[Double] = cost.lift(quantity)(_ * _)

  val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale.US)

  def currency(cell: Cell[Double]): NodeSeq => NodeSeq =
    WiringUI.toNode(cell)((value, ns) => Text(formatter format value))

  def increment(): JsCmd = {
    quantity.atomicUpdate(_ + 1)
    S.notice("Added One")
  }

  def render: CssSel =
    "#add [onclick]" #> ajaxInvoke(increment) &
      "#quantity *" #> WiringUI.asText(quantity) &
      "#subtotal *" #> currency(subtotal)

}
