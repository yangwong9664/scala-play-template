package controllers

import helpers.IntegrationSpecBase
import org.scalatest.WordSpec
import play.api.libs.ws.DefaultBodyWritables

class HomeControllerITSpec extends WordSpec with IntegrationSpecBase with DefaultBodyWritables {

  "home" should {
    "return a 200 for GET" in {
      val res = buildGetClient("/")
      whenReady(res) { response =>
        response.status.intValue() shouldBe 200
      }
    }
  }

}
