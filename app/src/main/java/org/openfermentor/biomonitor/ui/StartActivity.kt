package org.openfermentor.biomonitor.ui

import android.os.Bundle
import com.f2prateek.dart.HensonNavigable
import com.xmartlabs.bigbang.core.controller.SessionController
import com.xmartlabs.bigbang.ui.BaseAppCompatActivity
import org.openfermentor.biomonitor.helper.extensions.startFrom
import javax.inject.Inject

@HensonNavigable
class StartActivity : BaseAppCompatActivity() {
  @Inject
  internal lateinit var sessionController: SessionController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  
    if (sessionController.isSessionAlive) {
      Henson.with(context)
          .gotoMainActivity()
          .build()
          .startFrom(this)
    } else {
      Henson.with(context)
          .gotoLoginActivity()
          .build()
          .startFrom(this)
    }
    
    finish()
  }
}
