package models.daos

import java.util.UUID
import javax.inject.Inject

import com.mohiva.play.silhouette.api.LoginInfo
import models.{User, UserEntity}
import play.api.db.slick.DatabaseConfigProvider

import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext

/**
 * Give access to the user object.
 */
class UserDAOImpl @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider)
                           (implicit executionContext: ExecutionContext) extends BaseDAO(dbConfigProvider) with UserDAO {

  import driver.api._

  def all(): Future[Seq[UserEntity]] = db.run(Users.result)
  /**
   * Finds a user by its login info.
   *
   * @param loginInfo The login info of the user to find.
   * @return The found user or None if no user for the given login info could be found.
   */
  def find(loginInfo: LoginInfo): Future[Option[User]] = {
    val eventualMaybeEntity: Future[Option[UserEntity]] = db.run(Users.filter(_.providerID === loginInfo.providerID)
      .filter(_.providerKey === loginInfo.providerKey).result.headOption)

    eventualMaybeEntity.map { case (maybeEntity) =>
      maybeEntity match {
        case Some(e) => Option.apply(mapToUser(e))
        case None => Option.empty
      }
    }
  }

  /**
   * Finds a user by its user ID.
   *
   * @param userID The ID of the user to find.
   * @return The found user or None if no user for the given ID could be found.
   */
  def find(userID: UUID): Future[Option[User]] = {
    val eventualMaybeEntity: Future[Option[UserEntity]] = db.run(Users.filter(_.userID === userID.toString).result.headOption)
    eventualMaybeEntity.map { case (maybeEntity) =>
      maybeEntity match {
        case Some(e) => Option.apply(mapToUser(e))
        case None => Option.empty
      }
    }
  }

  /**
   * Saves a user.
   *
   * @param user The user to save.
   * @return The saved user.
   */
  def save(user: User) = {
    val insertQuery = Users returning Users.map(_.id) into ((product, id) => product.copy(id = id))
    val action = insertQuery += mapToEntity(user)
    db.run(action)

    //    users += (user.userID -> user)
    Future.successful(user)
  }

  def mapToEntity(user: User): UserEntity = {
    UserEntity(0, user.userID.toString, user.loginInfo.providerID, user.loginInfo.providerKey,
      user.firstName, user.lastName, user.fullName, user.email, user.avatarURL);
  }

  def mapToUser(e: UserEntity): User = {
    val userId = UUID.fromString(e.userID)
    val loginInfo = new LoginInfo(e.providerID, e.providerKey)
    User(userId, loginInfo, e.firstName, e.lastName, e.fullName, e.email, e.avatarURL)
  }
}

/**
 * The companion object.

object UserDAOImpl {

  /**
   * The list of users.
   */
  val users: mutable.HashMap[UUID, User] = mutable.HashMap()
}
  */