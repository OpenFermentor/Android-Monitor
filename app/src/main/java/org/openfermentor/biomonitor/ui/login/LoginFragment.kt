package org.openfermentor.biomonitor.ui.login

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import kotlinx.android.synthetic.main.fragment_login.*
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.helper.extensions.startFrom
import org.openfermentor.biomonitor.ui.Henson
import org.openfermentor.biomonitor.ui.common.base.BioMonitorFragment
import javax.inject.Inject
import android.view.inputmethod.InputMethodManager


@FragmentWithArgs
class LoginFragment : BioMonitorFragment<LoginView, LoginPresenter>(), LoginView {
  @Inject
  override lateinit var presenter: LoginPresenter
  
  override val layoutResId = R.layout.fragment_login
  
  override fun setup() {
    super.setup()
  
    progressBar.indeterminateTintList = ColorStateList.valueOf(Color.WHITE)
    activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    button.setOnClickListener { presenter.logIn(username.text, password.text) }
  }
  
  override fun showError(message: Int, title: Int, buttonTitle: Int) {
    super.showError(message, title, buttonTitle)
  
    button.visibility = View.VISIBLE
    progressBar.visibility = View.GONE
  }
  
  override fun showLoading() {
    button.visibility = View.GONE
    progressBar.visibility = View.VISIBLE
  
    if (isViewAlive && activity != null) {
      val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
      inputMethodManager?.hideSoftInputFromWindow(activity.currentFocus.windowToken, 0)
    }
  }
  
  override fun goToMainActivity() {
    Henson.with(context)
        .gotoMainActivity()
        .build()
        .startFrom(activity)
    activity.finish()
  }
}
