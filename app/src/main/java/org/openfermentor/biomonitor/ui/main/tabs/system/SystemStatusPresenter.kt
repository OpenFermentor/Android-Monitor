package org.openfermentor.biomonitor.ui.main.tabs.system

import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.controller.socket.SocketController
import org.openfermentor.biomonitor.helper.subscribers.GeneralObservableSubscriber
import org.openfermentor.biomonitor.model.Reading
import org.openfermentor.biomonitor.model.Status
import org.openfermentor.biomonitor.ui.common.base.BioMonitorPresenter
import org.openfermentor.biomonitor.ui.common.base.prepareForSubscription
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemStatus
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemVariable
import org.openfermentor.biomonitor.ui.main.tabs.system.model.SystemVariableStatus
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import javax.inject.Inject

class SystemStatusPresenter @Inject constructor() : BioMonitorPresenter<SystemStatusView>() {
  companion object {
    const val SERVER_TIMEOUT_SECONDS = 15L
    const val TIMEOUT_MAX_COUNT = 5
  }
  
  @Inject
  lateinit var socketController: SocketController
  
  private var runningStatusCount = 0
  private var runningUpdateCount = 0
  
  override fun attachView(view: SystemStatusView) {
    super.attachView(view)
    
    setNoConnectionStatus()
    listenForUpdates()
  }
  
  private fun listenForUpdates() {
    handleStatusStream()
    handleUpdateStream()
    handleExperimentRunningStatusStream()
  }
  
  private fun handleStatusStream(): Unit? = view?.let {
    socketController
        .status
        .timeout(SERVER_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .prepareForSubscription(it)
        .subscribe(object : GeneralObservableSubscriber<Status>() {
          override fun onNext(t: Status) {
            runningStatusCount = 0
            view?.changeStatus(SystemStatus.NO_RUNNING_EXPERIMENT)
  
            val variables = listOf(
                SystemVariable.createWithConnection(R.drawable.ic_thermometer,
                    App.context.getString(R.string.temperature), t.temp.connected),
                SystemVariable.createWithConnection(R.drawable.ic_ph,
                    App.context.getString(R.string.ph), t.ph.connected),
                SystemVariable.createWithConnection(R.drawable.ic_pump,
                    App.context.getString(R.string.peristaltic_pump), t.pumps.connected)
            )
            
            view?.loadVariables(variables)
          }
          
          override fun onError(throwable: Throwable) {
            super.onError(throwable)
            
            if (throwable is TimeoutException) {
              runningStatusCount += 1
              resetIfNeeded()
              handleStatusStream()
            }
          }
        })
  }
  
  private fun handleUpdateStream(): Unit? = view?.let {
    socketController
        .lastReading
        .timeout(SERVER_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .prepareForSubscription(it)
        .subscribe(object : GeneralObservableSubscriber<Reading>() {
          override fun onNext(t: Reading) {
            runningUpdateCount = 0
            view?.changeStatus(SystemStatus.RUNNING_EXPERIMENT)
    
            val variables = listOf(
                SystemVariable(R.drawable.ic_thermometer, t.temp.toString(),
                    App.context.getString(R.string.temperature_unit), SystemVariableStatus.NORMAL),
                SystemVariable(R.drawable.ic_ph, t.ph.toString(), null, SystemVariableStatus.NORMAL)
            )
    
            view?.loadVariables(variables)
          }
  
          override fun onError(throwable: Throwable) {
            super.onError(throwable)
    
            if (throwable is TimeoutException) {
              runningUpdateCount += 1
              resetIfNeeded()
              handleUpdateStream()
            }
          }
        })
  }
  
  private fun handleExperimentRunningStatusStream(): Unit? = view?.let {
    socketController
        .runningExperiment
        .timeout(SERVER_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .prepareForSubscription(it)
        .subscribe(object : GeneralObservableSubscriber<Boolean>() {
          override fun onNext(t: Boolean) {
            if (!t) {
              setNoConnectionStatus()
            }
          }
  
          override fun onError(throwable: Throwable) {
            super.onError(throwable)
            handleExperimentRunningStatusStream()
          }
        })
  }
  
  private fun resetIfNeeded() {
    if (runningStatusCount >= TIMEOUT_MAX_COUNT && runningUpdateCount >= TIMEOUT_MAX_COUNT) {
      runningStatusCount = 0
      runningUpdateCount = 0
      setNoConnectionStatus()
    }
  }
  
  private fun setNoConnectionStatus() {
    val variables = listOf(
        SystemVariable.createWithConnection(R.drawable.ic_thermometer, App.context.getString(R.string.temperature)),
        SystemVariable.createWithConnection(R.drawable.ic_ph, App.context.getString(R.string.ph)),
        SystemVariable.createWithConnection(R.drawable.ic_pump, App.context.getString(R.string.peristaltic_pump))
    )
  
    view?.changeStatus(SystemStatus.NO_CONNECTION)
    view?.loadVariables(variables)
  }
}
