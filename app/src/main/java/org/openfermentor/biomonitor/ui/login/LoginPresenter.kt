package org.openfermentor.biomonitor.ui.login

import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.controller.user.UserController
import org.openfermentor.biomonitor.helper.subscribers.GeneralCompletableSubscriber
import org.openfermentor.biomonitor.ui.common.base.BioMonitorPresenter
import org.openfermentor.biomonitor.ui.common.base.prepareForSubscription
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BioMonitorPresenter<LoginView>() {
  @Inject
  lateinit var userController: UserController
  
  fun logIn(email: String, password: String) {
    if (email.isBlank() || password.isBlank()) {
      view?.showError(R.string.empty_login_fields, R.string.error)
      return
    }
    
    view?.showLoading()
    view?.let {
      userController
          .login(email, password)
          .toCompletable()
          .prepareForSubscription(it)
          .subscribe(object : GeneralCompletableSubscriber() {
            override fun onComplete() {
              view?.goToMainActivity()
            }
  
            override fun onError(throwable: Throwable) {
              super.onError(throwable)
              
              view?.showError(R.string.error_while_authenticating, R.string.error)
            }
          })
    }
  }
}
