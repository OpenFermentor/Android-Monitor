package org.openfermentor.biomonitor.controller.socket

enum class ChannelEvent {
  OK,
  ERROR,
  STATUS,
  UPDATE,
  STARTED,
  STOPPED,
  ALERT,
  INSTRUCTION,
  ;
  
  override fun toString() = super.toString().toLowerCase()
}
