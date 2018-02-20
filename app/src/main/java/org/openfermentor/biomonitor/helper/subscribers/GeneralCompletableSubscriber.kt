package org.openfermentor.biomonitor.helper.subscribers

import android.support.annotation.CheckResult
import android.support.annotation.StringRes
import org.openfermentor.biomonitor.ui.common.base.BioMonitorView
import io.reactivex.CompletableObserver
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

open class GeneralCompletableSubscriber constructor(bioMonitorView: BioMonitorView? = null) : CompletableObserver {
  private val viewReference = WeakReference<BioMonitorView>(bioMonitorView)

  override fun onSubscribe(disposable: Disposable) { }

  override fun onComplete() { }

  override fun onError(throwable: Throwable) {
    val view = viewReference.get()
    if (alertOnError(throwable) && view != null && view.isViewAlive) {
      view.showError(throwable, getErrorMessage(throwable))
    }
  }

  @StringRes
  protected open fun getErrorMessage(throwable: Throwable): Int? = null

  @CheckResult
  protected open fun alertOnError(throwable: Throwable) = true
}
