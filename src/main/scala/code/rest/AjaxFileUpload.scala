package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.{BadRequestResponse, OkResponse, ResponseWithReason}

object AjaxFileUpload extends RestHelper {

  serve {

    case "upload" :: Nil Post req =>
      for (file <- req.uploadedFiles) {
        println("Received %s of type %s" format(file.fileName, file.mimeType))
      }

      if (req.uploadedFiles.exists( _.mimeType != "image/png" ))
        ResponseWithReason(BadRequestResponse(), "Only PNGs")
      else
        OkResponse()

  }

}
