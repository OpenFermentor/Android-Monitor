package org.openfermentor.biomonitor.ui

import android.content.Intent
import android.os.Bundle
import com.f2prateek.dart.HensonNavigable
import com.xmartlabs.bigbang.core.controller.SessionController
import com.xmartlabs.bigbang.ui.BaseAppCompatActivity
import org.openfermentor.biomonitor.controller.session
import javax.inject.Inject

@HensonNavigable
class StartActivity : BaseAppCompatActivity() {
  @Inject
  internal lateinit var sessionController: SessionController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val session = sessionController.session
    if (session == null) {
      //TODO: handle on start activity no session
    } else {
      TODO("Handle on start activity with session")
    }
  }
}
