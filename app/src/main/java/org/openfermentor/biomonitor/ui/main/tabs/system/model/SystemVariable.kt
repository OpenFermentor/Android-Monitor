package org.openfermentor.biomonitor.ui.main.tabs.system.model

data class SystemVariable(
    var icon: Int,
    var text: String,
    var unit: String?,
    var status: ISystemVariableStatus
) {
  companion object {
    @JvmStatic
    fun createWithConnection(icon: Int, text: String, connected: Boolean = false) =
        SystemVariable(icon, text, null, if (connected) SystemVariableStatus.ON else SystemVariableStatus.OFF)
  }
}
