package org.openfermentor.biomonitor.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime

class LogEntry {
  var type = LogEntryType.READING_ERROR
  @SerializedName("insertedAt")
  var date = LocalDateTime.now()
  var description = ""
}
