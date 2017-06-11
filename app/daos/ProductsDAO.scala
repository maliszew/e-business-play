package daos

import javax.inject.Inject

import models.{Products, ProductsREST}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by kprzystalski on 23/04/17.
  */

class ProductsDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  val Products = TableQuery[ProductsTable]

  def all(implicit ec: ExecutionContext): Future[List[ProductsREST]] = {
    val query =  Products
    val results = query.result
    val futureProducts = db.run(results)
    futureProducts.map(
      _.map {
        a => ProductsREST(prodId = a.prodId, tytul = a.tytul, opis = a.opis, imgUrl = a.imgUrl, cena = a.cena, kategoriaId = a.kategoriaId)
      }.toList)
  }

  def getById(prodId: Long): Future[Option[ProductsREST]] = {
    val product = db.run(Products.filter(_.prodId === prodId).result.headOption)
    product.map(
      _.map {
        a => ProductsREST(prodId = a.prodId, tytul = a.tytul, opis = a.opis, imgUrl = a.imgUrl, cena = a.cena, kategoriaId = a.kategoriaId)
      }
    )
  }

  def getByCategory(catId: Long): Future[List[ProductsREST]] = {
    val products = db.run(Products.filter(_.kategoriaId === catId).result)
    products.map(
      _.map {
        a => ProductsREST(prodId = a.prodId, tytul = a.tytul, opis = a.opis, imgUrl = a.imgUrl, cena = a.cena, kategoriaId = a.kategoriaId)
      }.toList)
  }

  def insert(product: Products): Future[Unit] = {
    db.run(Products += product).map { _ => () }
  }

  def update(prodId: Long, product: Products): Future[Unit] = {
    val toUpdate: Products = product.copy(prodId)
    db.run(Products.filter(_.prodId === prodId).update(toUpdate)).map(_ => ())
  }

  def delete(prodId: Long): Future[Unit] = {
    db.run(Products.filter(_.prodId === prodId).delete).map(_ => () )
  }

  class ProductsTable(tag: Tag) extends Table[Products](tag, "Products") {
    def prodId = column[Long]("prodId",O.AutoInc, O.AutoInc)
    def tytul = column[String]("tytul")
    def opis = column[String]("opis")
    def imgUrl = column[String]("imgUrl")
    def cena = column[BigDecimal]("cena")
    def kategoriaId = column[Long]("kategoriaId")
    def * = (prodId, tytul, opis, imgUrl, cena, kategoriaId) <> (models.Products.tupled, models.Products.unapply)
  }

}
