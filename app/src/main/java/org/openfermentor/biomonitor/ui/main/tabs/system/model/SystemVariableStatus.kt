package org.openfermentor.biomonitor.ui.main.tabs.system.model

import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R

enum class SystemVariableStatus(
    textRes: Int,
    textColorRes: Int,
    iconRes: Int?
) : ISystemVariableStatus {
  NORMAL(R.string.good, R.color.green, null),
  BALANCING(R.string.balancing, R.color.orange, null),
  OVER_RANGE(R.string.over_supported_range, R.color.red, null),
  UNDER_RANGE(R.string.under_supported_range, R.color.red, null),
  ON(R.string.on, R.color.green, null),
  OFF(R.string.off, R.color.red, null),
  ;
  
  override val icon: Drawable? = iconRes?.let { ContextCompat.getDrawable(App.context, it) }
  override val text: String = App.context.getString(textRes)
  override val textColor: Int = ContextCompat.getColor(App.context, textColorRes)
}
