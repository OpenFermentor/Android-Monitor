package org.openfermentor.biomonitor.controller.user

import com.xmartlabs.bigbang.core.controller.Controller
import com.xmartlabs.bigbang.core.controller.SessionController
import com.xmartlabs.bigbang.core.extensions.observeOnIo
import com.xmartlabs.bigbang.core.extensions.subscribeOnIo
import org.openfermentor.biomonitor.controller.common.session
import org.openfermentor.biomonitor.model.Session
import org.openfermentor.biomonitor.model.User
import org.openfermentor.biomonitor.service.UserService
import org.openfermentor.biomonitor.service.request.LoginRequest
import javax.inject.Inject

class UserController : Controller() {
  @Inject
  lateinit var sessionController: SessionController
  @Inject
  lateinit var userService: UserService
  
  fun login(email: String, password: String) = userService
      .login(LoginRequest(User().apply {
        this.email = email
        this.password = password
      }))
      .observeOnIo()
      .subscribeOnIo()
      .doOnSuccess { sessionController.session = Session(it.token) }
      .applyIoSchedulers()
}
