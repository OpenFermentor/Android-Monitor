package org.openfermentor.biomonitor.model

import com.google.gson.annotations.SerializedName

class Status {
  var temp = StatusValue.OFF
  var pumps = StatusValue.OFF
  var ph = StatusValue.OFF
}

enum class StatusValue {
  @SerializedName("1")
  ON,
  @SerializedName("0")
  OFF,
  ;
  
  val connected
    get() = this == ON
}
