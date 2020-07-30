package helpers

import akka.http.scaladsl.model.HttpResponse
import app.{AppConfig, ApplicationConfig}
import com.google.inject.{Inject, Injector, Provider}
import org.scalatest._
import org.scalatest.concurrent.{Eventually, IntegrationPatience, ScalaFutures}
import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import play.api.inject.ConfigurationProvider
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.{Application, Configuration, Environment, Mode}
import play.api.inject.bind

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import play.api.inject.guice.GuiceInjectorBuilder
trait IntegrationSpecBase extends WordSpec
  with GivenWhenThen with TestSuite with ScalaFutures with IntegrationPatience with Matchers
  with WiremockHelper
  with GuiceOneServerPerSuite
  with BeforeAndAfterEach with BeforeAndAfterAll with Eventually {

  val mockHost: String = WiremockHelper.wiremockHost
  val mockPort: Int = WiremockHelper.wiremockPort
  val mockUrl = s"http://$mockHost:$mockPort"

  override implicit lazy val app: Application = new GuiceApplicationBuilder()
    .overrides(bind(classOf[AppConfig]) to(classOf[TestConfig]))
    .build()

  def statusOf(res: Future[HttpResponse])(implicit timeout: Duration): Int = Await.result(res, timeout).status.intValue()

  override def beforeEach(): Unit = {
    resetWiremock()
  }

  override def beforeAll(): Unit = {
    super.beforeAll()
    startWiremock()
  }

  override def afterAll(): Unit = {
    stopWiremock()
    super.afterAll()
  }
}

class TestConfig extends ApplicationConfig {

}
