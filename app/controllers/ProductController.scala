package controllers

import javax.inject.Inject

import daos.ProductsDAO
import models.{Products, ProductsREST}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Json
import play.api.mvc._

class ProductController @Inject()(productsDAO: ProductsDAO) extends Controller {

  def allproducts = Action.async { implicit  request =>
    productsDAO.all map {
      products => Ok(Json.toJson(products))
    }
  }

  def newproduct = Action { implicit request =>
    var json:ProductsREST = request.body.asJson.get.as[ProductsREST]
    var product = Products(prodId = 0, tytul = json.tytul, opis = json.opis)
    productsDAO.insert(product)
    Ok(request.body.asJson.get)
  }
}
