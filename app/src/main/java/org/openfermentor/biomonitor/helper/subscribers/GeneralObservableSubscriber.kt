package org.openfermentor.biomonitor.helper.subscribers

import android.support.annotation.CheckResult
import android.support.annotation.StringRes
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

import org.openfermentor.biomonitor.ui.common.base.BioMonitorView

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

import java.lang.ref.WeakReference

open class GeneralObservableSubscriber<T> constructor(bioMonitorView: BioMonitorView? = null) : Observer<T> {
  private val viewReference = WeakReference<BioMonitorView>(bioMonitorView)
  
  override fun onSubscribe(d: Disposable) { }

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
