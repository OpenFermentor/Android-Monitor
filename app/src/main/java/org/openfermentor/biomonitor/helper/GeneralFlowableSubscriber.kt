package org.openfermentor.biomonitor.helper

import android.support.annotation.CheckResult
import android.support.annotation.StringRes
import org.openfermentor.biomonitor.ui.common.BioMonitorView
import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscription
import java.lang.ref.WeakReference

open class GeneralFlowableSubscriber<T> constructor(bioMonitorView: BioMonitorView? = null) : FlowableSubscriber<T> {
  protected val maxNumberOfElements = java.lang.Long.MAX_VALUE
  private val viewReference: WeakReference<BioMonitorView> = WeakReference<BioMonitorView>(bioMonitorView)

  override fun onSubscribe(subscription: Subscription) = subscription.request(maxNumberOfElements)

  override fun onNext(t: T) { }

  override fun onError(throwable: Throwable) {
    val view = viewReference.get()
    if (alertOnError(throwable) && view != null && view.isViewAlive) {
      view.showError(throwable, getErrorMessage(throwable))
    }
  }

  override fun onComplete() { }

  @StringRes
  protected open fun getErrorMessage(throwable: Throwable): Int? = null

  @CheckResult
  protected open fun alertOnError(throwable: Throwable) = true
}
