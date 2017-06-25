package models.daos

import javax.inject.Inject

import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext

class BaseDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                       (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  protected val Users = TableQuery[UsersTable]

  protected class UsersTable(tag: Tag) extends Table[UserEntity](tag, "Users") {
    def id = column[Long]("id", O.AutoInc, O.PrimaryKey)
    def userID = column[String]("userID", O.PrimaryKey)
    def providerID = column[String]("providerID")
    def providerKey = column[String]("providerKey")
    def firstName = column[Option[String]]("firstName")
    def lastName = column[Option[String]]("lastName")
    def fullName = column[Option[String]]("fullName")
    def email = column[Option[String]]("email")
    def avatarURL = column[Option[String]]("avatarURL")

    override def * = (id, userID, providerID, providerKey, firstName, lastName, fullName, email, avatarURL) <> (UserEntity.tupled, UserEntity.unapply)
  }


}
