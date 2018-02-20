package org.openfermentor.biomonitor.ui.common.base

import android.support.annotation.StringRes

import com.xmartlabs.bigbang.ui.mvp.MvpView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.openfermentor.biomonitor.R

interface BioMonitorView : MvpView {
  fun <T : Any> keepAliveWhileVisible(source: Flowable<T>): Flowable<T>
  fun <T : Any> keepAliveWhileVisible(source: Maybe<T>): Maybe<T>
  fun <T : Any> keepAliveWhileVisible(source: Observable<T>): Observable<T>
  fun <T : Any> keepAliveWhileVisible(source: Single<T>): Single<T>
  fun keepAliveWhileVisible(source: Completable): Completable
  fun setup()
  fun showError(@StringRes message: Int, @StringRes title: Int = R.string.error,
                @StringRes buttonTitle: Int = android.R.string.ok)
  fun showError(error: Throwable, @StringRes message: Int?)
  val isViewAlive: Boolean
}
