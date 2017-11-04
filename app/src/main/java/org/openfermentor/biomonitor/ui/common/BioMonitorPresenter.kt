package org.openfermentor.biomonitor.ui.common

import com.xmartlabs.bigbang.ui.mvp.BaseMvpPresenter

abstract class BioMonitorPresenter<T: BioMonitorView> : BaseMvpPresenter<T>() {
  override fun attachView(view: T) {
    super.attachView(view)
    view.setup()
  }
}
