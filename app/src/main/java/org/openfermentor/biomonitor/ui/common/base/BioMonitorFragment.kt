package org.openfermentor.biomonitor.ui.common.base

import android.support.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.FragmentEvent
import com.xmartlabs.bigbang.core.extensions.observeOnMain
import com.xmartlabs.bigbang.ui.mvp.BaseMvpFragment
import com.xmartlabs.bigbang.ui.mvp.MvpPresenter
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import org.openfermentor.biomonitor.R
import io.reactivex.exceptions.CompositeException
import java.io.IOException
import java.util.concurrent.CancellationException

abstract class BioMonitorFragment<V : BioMonitorView, P : MvpPresenter<V>> : BaseMvpFragment<V, P>(), BioMonitorView {
  override val isViewAlive: Boolean
    get() = isAdded && activity != null

  override fun setup() = Unit

  override fun showError(message: Int, title: Int, buttonTitle: Int) {
    if (isViewAlive) {
      context?.let {
        MaterialDialog.Builder(it)
            .title(title)
            .content(message)
            .positiveText(buttonTitle)
            .build()
            .show()
      }
    }
  }

  override fun showError(error: Throwable, @StringRes message: Int?) {
    if (error is CancellationException) {
      return
    }

    var receiverError = error
    if (receiverError is CompositeException) {
      receiverError = receiverError.exceptions[0]
    }
    if (receiverError is IOException) {
      showError(R.string.check_your_internet_connection, R.string.no_internet_connection)
    } else if (message == null) {
      showError(R.string.error_service_call_generic)
    } else {
      showError(message)
    }
  }
  
  override fun <T : Any> keepAliveWhileVisible(source: Observable<T>) =
      source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW)).observeOnMain()
  
  override fun <T : Any> keepAliveWhileVisible(source: Single<T>) =
      source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW)).observeOnMain()
  
  override fun <T : Any> keepAliveWhileVisible(source: Flowable<T>) =
      source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW)).observeOnMain()
  
  override fun <T : Any> keepAliveWhileVisible(source: Maybe<T>) =
      source.compose(RxLifecycle.bindUntilEvent(lifecycle(), FragmentEvent.DESTROY_VIEW)).observeOnMain()
  
  override fun keepAliveWhileVisible(source: Completable) =
      source.compose(RxLifecycle.bindUntilEvent<FragmentEvent, FragmentEvent>(lifecycle(), FragmentEvent.DESTROY_VIEW))
          .observeOnMain()
}

fun <T : Any> Observable<T>.prepareForSubscription(view: BioMonitorView) = view.keepAliveWhileVisible(this)
fun <T : Any> Single<T>.prepareForSubscription(view: BioMonitorView) = view.keepAliveWhileVisible(this)
fun <T : Any> Flowable<T>.prepareForSubscription(view: BioMonitorView) = view.keepAliveWhileVisible(this)
fun <T : Any> Maybe<T>.prepareForSubscription(view: BioMonitorView) = view.keepAliveWhileVisible(this)
fun Completable.prepareForSubscription(view: BioMonitorView) = view.keepAliveWhileVisible(this)
