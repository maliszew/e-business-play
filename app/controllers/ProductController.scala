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

  def getproduct(prodId: Long) = Action.async { implicit request =>
    productsDAO.getById(prodId) map {
      product => Ok(Json.toJson(product))
    }
  }

  def newproduct = Action { implicit request =>
    var json:ProductsREST = request.body.asJson.get.as[ProductsREST]
    var product = Products(prodId = 0, tytul = json.tytul, opis = json.opis, imgUrl = json.imgUrl, cena = json.cena, kategoriaId = json.kategoriaId)
    productsDAO.insert(product)
    Ok(request.body.asJson.get)
  }

}
