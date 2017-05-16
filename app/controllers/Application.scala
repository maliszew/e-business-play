package controllers

import javax.inject.Inject

import daos.ProductsDAO
import models.ProductsREST
import models.Products
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class Application @Inject() (productsDAO: ProductsDAO) extends Controller {

  def index = Action {
    Ok(views.html.index("Play Shop App - E-Business project!"))
  }
}
