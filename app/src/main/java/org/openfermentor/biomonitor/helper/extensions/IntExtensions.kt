package org.openfermentor.biomonitor.helper.extensions

import org.threeten.bp.Duration

private object Constants {
  val ONE_HOUR_DURATION = Duration.ofHours(1)
  val ONE_MINUTE_DURATION = Duration.ofMinutes(1)
}

fun Int.secondsToHourMinutes() = toLong().secondsToHourMinutes()

fun Long.secondsToHourMinutes(): String {
  val hours = this / Constants.ONE_HOUR_DURATION.seconds
  val minutes = (this % Constants.ONE_HOUR_DURATION.seconds) / Constants.ONE_MINUTE_DURATION.seconds
  
  val time = if (hours > 0) "${hours}h " else ""
  return "$time${minutes}m"
}
