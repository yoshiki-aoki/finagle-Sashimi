package com.sumioturk.sashimi.service.sushi

import com.twitter.finagle.{Service, SimpleFilter}
import config.SashimiConfig
import domain.User
import infrastructure.UserFutureRepository
import com.twitter.finagle.http.{Request, Response}
import com.sumioturk.sashimi.common.Message
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import scala.Some
import com.sumioturk.sashimi.infrastructure.SessionPool
import com.sumioturk.sashimi.service.ServiceUtilities._
import com.sumioturk.sashimi.service.CommonService

class AuthorizationFilter(commons: CommonService) extends SimpleFilter[Request, Response] {
  val userRepo = new UserFutureRepository(commons.redis)
  val sessionPool = new SessionPool(commons.redis)
  def apply(request: Request, continue: Service[Request, Response]) = {
    val key = Option(User.SessionKeyPrefix + request.getParam(User.Key) + User.SessionKeySuffix)
    val skregex = User.SessionKeyRegex
    val session =
      if (key.exists(s => s.contains("null")))
        Option(request.getHeader(User.Cookie))
      else
        key
    session match {
      case Some(s) =>
        val sk =
          skregex.findFirstIn(s) match {
            case Some(x) =>
              x.substring(User.SessionKeyPrefix.length, x.length - User.SessionKeySuffix.length)
            case _ =>
              User.DummyCookie
        }
        sessionPool.resolve(sk) flatMap {
          case None =>
            JsonResponse(Message.toJson(Message.CouldNotAuthorize), FORBIDDEN)
          case Some(userId) =>
            request.addHeader(User.Identity, userId)
            continue(request)
        }
      case _ =>
        JsonResponse(Message.toJson(Message.CouldNotAuthorize), FORBIDDEN)
    }
  }
}
