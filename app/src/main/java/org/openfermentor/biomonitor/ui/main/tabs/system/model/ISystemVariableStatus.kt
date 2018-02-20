package org.openfermentor.biomonitor.ui.main.tabs.system.model

import android.graphics.drawable.Drawable

interface ISystemVariableStatus {
  val icon: Drawable?
  val text: String
  val textColor: Int
}
