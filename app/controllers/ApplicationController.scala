package controllers

import javax.inject.Inject

import com.mohiva.play.silhouette.api.{ HandlerResult, LogoutEvent, Silhouette }
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.libs.json.{ JsValue, Json }
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import utils.auth.DefaultEnv

import scala.concurrent.Future

/**
 * The basic application controller.
 *
 * @param messagesApi The Play messages API.
 * @param silhouette The Silhouette stack.
 * @param socialProviderRegistry The social provider registry.
 */
class ApplicationController @Inject() (
  val messagesApi: MessagesApi,
  silhouette: Silhouette[DefaultEnv],
  socialProviderRegistry: SocialProviderRegistry)
  extends Controller with I18nSupport {

  /**
   * Returns the user.
   *
   * @return The result to display.
   */
  def user = silhouette.SecuredAction.async { implicit request =>
    Future.successful(Ok(Json.toJson(request.identity)))
  }

  /**
   * Manages the sign out action.
   */
  def signOut = silhouette.SecuredAction.async { implicit request =>
    silhouette.env.eventBus.publish(LogoutEvent(request.identity, request))
    silhouette.env.authenticatorService.discard(request.authenticator, Ok)
  }

  /**
    * An example for a user-aware request handler.
    */
  def oauthSignIn = Action.async { implicit request =>
    silhouette.UserAwareRequestHandler { userAwareRequest =>
      Future.successful(HandlerResult(Ok, userAwareRequest.identity))
    }.map {
      case HandlerResult(r, Some(user)) => Ok(Json.toJson(user))
      case HandlerResult(r, None) => Unauthorized
    }
  }

  /**
   * Provides the desired template.
   *
   * @param template The template to provide.
   * @return The template.
   */
  def view(template: String) = silhouette.UserAwareAction { implicit request =>
    template match {
      case "home" => Ok(views.html.home())
      case "signUp" => Ok(views.html.signUp())
      case "signIn" => Ok(views.html.signIn(socialProviderRegistry))
      case "navigation" => Ok(views.html.navigation())
      case _ => NotFound
    }
  }

  def echo = Action { request =>
    Ok("Got request [" + request + "]")
  }
}
