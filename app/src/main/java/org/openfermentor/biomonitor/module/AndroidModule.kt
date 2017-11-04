package org.openfermentor.biomonitor.module

import android.app.Application
import com.xmartlabs.bigbang.core.model.BuildInfo

import com.xmartlabs.bigbang.core.module.CoreAndroidModule
import org.openfermentor.biomonitor.model.common.BuildInfo as CoreBuildInfo

class AndroidModule(application: Application) : CoreAndroidModule(application) {
  override fun provideBuildInformation(): BuildInfo = CoreBuildInfo()
}
