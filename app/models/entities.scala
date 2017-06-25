package models

import java.sql.Timestamp

import play.api.libs.json.Format

/**
  * Created by kprzystalski on 23/04/17.
  */

case class Products(prodId: Long, tytul: String, opis: String, imgUrl: String, cena: BigDecimal, kategoriaId: Long)

//, catId: Long, createdAt: Timestamp

//case class Categories(catId: Long, tytul: String)

//case class Purchases(purId: Long, prodId: Long, userId: Long)

case class UserEntity(id: Long, userID: String, providerID: String, providerKey: String, firstName: Option[String],
                      lastName: Option[String], fullName: Option[String], email: Option[String], avatarURL: Option[String])