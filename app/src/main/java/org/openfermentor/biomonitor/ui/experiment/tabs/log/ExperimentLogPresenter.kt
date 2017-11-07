package org.openfermentor.biomonitor.ui.experiment.tabs.log

import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.controller.experiment.ExperimentController
import org.openfermentor.biomonitor.helper.subscribers.GeneralSingleSubscriber
import org.openfermentor.biomonitor.model.LogEntry
import org.openfermentor.biomonitor.ui.common.base.BioMonitorPresenter
import org.openfermentor.biomonitor.ui.common.base.prepareForSubscription
import javax.inject.Inject

/**
 * Created by diegomedina on 11/5/17.
 */
class ExperimentLogPresenter @Inject constructor() : BioMonitorPresenter<ExperimentLogView>() {
  @Inject
  lateinit var experimentController: ExperimentController
  
  override fun attachView(view: ExperimentLogView) {
    super.attachView(view)
    
    loadLog(view)
  }
  
  private fun loadLog(aView: ExperimentLogView) = experimentController
      .getExperimentLog(aView.experiment.id)
      .prepareForSubscription(aView)
      .subscribe(object : GeneralSingleSubscriber<List<LogEntry>>() {
        override fun onSuccess(t: List<LogEntry>) {
          super.onSuccess(t)
          
          if (t.isEmpty()) {
            view?.showMessage(App.context.getString(R.string.no_log_entries))
          } else {
            view?.logEntriesLoaded(t)
          }
        }
  
        override fun onError(throwable: Throwable) {
          super.onError(throwable)
          
          view?.showMessage(App.context.getString(R.string.generic_error))
        }
      })
}
