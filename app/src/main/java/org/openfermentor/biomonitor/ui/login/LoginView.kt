package org.openfermentor.biomonitor.ui.login

import org.openfermentor.biomonitor.ui.common.base.BioMonitorView

interface LoginView : BioMonitorView {
  fun showLoading()
  fun goToMainActivity()
}
