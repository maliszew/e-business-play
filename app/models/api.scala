package models

import play.api.libs.json.Json

/**
  * Created by kprzystalski on 23/04/17.
  */

case class ProductsREST(prodId: Long, tytul: String, opis: String, imgUrl: String, cena: BigDecimal, kategoriaId: Long)


object ProductsREST {
  implicit val productsFormat = Json.format[ProductsREST]
}

/* case class NewProductsREST(tytul: String, opis: String, imgUrl: String, cena: BigDecimal, kategoriaId: Long)

object NewProductsREST {
  implicit val productFormat = Json.format[NewProductsREST]
} */
