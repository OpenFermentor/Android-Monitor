package org.openfermentor.biomonitor.model.common

import org.openfermentor.biomonitor.BuildConfig
import com.xmartlabs.bigbang.core.model.BuildInfo as CoreBuildInfo

class BuildInfo : CoreBuildInfo {
  override val isDebug = BuildConfig.DEBUG
  override val isProduction: Boolean
    get() = BuildConfig.FLAVOR.contains(BuildType.PRODUCTION.toString(), ignoreCase = true)
  override val isStaging: Boolean
    get() = BuildConfig.FLAVOR.contains(BuildType.STAGING.toString(), ignoreCase = true)
}
