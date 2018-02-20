package org.openfermentor.biomonitor.controller.socket

import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import org.openfermentor.biomonitor.helper.subscribers.GeneralObservableSubscriber
import org.openfermentor.biomonitor.model.Error
import org.openfermentor.biomonitor.model.Reading
import org.openfermentor.biomonitor.model.Status
import org.phoenixframework.channels.Socket
import java.util.concurrent.TimeUnit
import org.phoenixframework.channels.ChannelEvent as PhxChannelEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketController @Inject constructor() {
  @Inject
  lateinit var gson: Gson
  
  val error = PublishSubject.create<String>()
  val status = PublishSubject.create<Status>()
  val alert = PublishSubject.create<Error>()
  val runningExperiment = PublishSubject.create<Boolean>()
  val lastReading = PublishSubject.create<Reading>()
  
  private val socket = Socket(App.context.getString(R.string.base_url_socket))
  
  init {
    socket.connect()
    socket.onOpen {
      handleStatusChannel()
      handleExperimentChannel()
      handleInstructionChannel()
    }
    socket.onClose { App.context.getString(R.string.generic_error) }
  }
  
  private fun handleStatusChannel() {
    val channel = socket.chan(ChannelType.STATUS.channelName, null)
  
    channel
        ?.join()
        ?.receive(ChannelEvent.ERROR.toString()) {
          error.onNext(App.context.getString(R.string.generic_error))
        }
  
    channel
        .on(ChannelEvent.ERROR.toString()) { error.onNext(it.payload.toString()) }
        .on(ChannelEvent.STATUS.toString()) { status.onNext(gson.fromJson(it.payload.toString(), Status::class.java)) }
        .on(PhxChannelEvent.ERROR.phxEvent) { error.onNext(it.payload.toString()) }
  }
  
  private fun handleExperimentChannel() {
    val channel = socket.chan(ChannelType.EXPERIMENT.channelName, null)
  
    channel
        ?.join()
        ?.receive(ChannelEvent.ERROR.toString()) {
          error.onNext(App.context.getString(R.string.generic_error))
        }
  
    channel
        .on(ChannelEvent.ERROR.toString()) { notifyError(it.payload.toString()) }
        .on(ChannelEvent.STARTED.toString()) { runningExperiment.onNext(true) }
        .on(ChannelEvent.STOPPED.toString()) { runningExperiment.onNext(false) }
        .on(ChannelEvent.UPDATE.toString()) { evt ->
          lastReading.onNext(gson.fromJson(evt.payload.toString(), Reading::class.java))
        }
        .on(ChannelEvent.ALERT.toString()) { handleAlert(it.payload.toString()) }
        .on(PhxChannelEvent.ERROR.phxEvent) { notifyError(it.payload.toString()) }
  }
  
  private fun notifyError(errorMessage: String) {
    error.onNext(errorMessage)
    App.context.showNotification(errorMessage)
  }
  
  private fun handleAlert(payload: String) {
    val alertError = gson.fromJson(payload, Error::class.java)
    
    alert.onNext(alertError)
    App.context.showNotification(alertError.message)
  }
  
  private fun handleInstructionChannel() {
    val channel = socket.chan(ChannelType.INSTRUCTION.channelName, null)
    
    channel
        ?.join()
        ?.receive(ChannelEvent.ERROR.toString()) {
          error.onNext(App.context.getString(R.string.generic_error))
        }
  
    channel
        .on(ChannelEvent.ERROR.toString()) { error.onNext(it.payload.toString()) }
        .on(ChannelEvent.INSTRUCTION.toString()) {
          App.context.showInstruction(gson.fromJson(it.payload.toString(), MessageResponse::class.java).message)
        }
        .on(PhxChannelEvent.ERROR.phxEvent) { error.onNext(it.payload.toString()) }
  }
}
