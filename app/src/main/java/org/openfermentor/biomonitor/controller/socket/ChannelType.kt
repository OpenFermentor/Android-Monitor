package org.openfermentor.biomonitor.controller.socket

enum class ChannelType(val channelName: String) {
  STATUS("sensors"),
  EXPERIMENT("routine"),
  INSTRUCTION("instructions"),
  ;
}
