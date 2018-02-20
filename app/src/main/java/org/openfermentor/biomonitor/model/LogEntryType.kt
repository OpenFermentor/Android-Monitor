package org.openfermentor.biomonitor.model

import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import com.google.gson.annotations.SerializedName
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R

/**
 * Created by diegomedina on 12/18/17.
 */
enum class LogEntryType(
    @StringRes private val titleRes: Int,
    @ColorRes private val colorRes: Int
) {
  @SerializedName("reading_error")
  READING_ERROR(R.string.reading_error, R.color.fluid_grey),
  @SerializedName("base_cal")
  BASE_CALIBRATION(R.string.base_calibration, R.color.fluid_sky_blue),
  @SerializedName("acid_cal")
  ACID_CALIBRATION(R.string.acid_calibration, R.color.fluid_pink),
  @SerializedName("temp_change")
  TEMPERATURE_CHANGE(R.string.temperature_change, R.color.fluid_orange),
  @SerializedName("system_error")
  SYSTEM_ERROR(R.string.system_error, R.color.fluid_lime),
  ;
  
  val title
      get() = App.context.getString(titleRes)
  
  val color
      @ColorInt get() = ContextCompat.getColor(App.context, colorRes)
}
