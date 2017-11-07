package org.openfermentor.biomonitor.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.xmartlabs.bigbang.core.model.BuildInfo
import org.openfermentor.biomonitor.module.converter.GsonLocalDateTimeAdapter
import org.threeten.bp.LocalDateTime
import com.xmartlabs.bigbang.retrofit.module.ServiceGsonModule as CoreServiceGsonModule

class ServiceGsonModule : CoreServiceGsonModule() {
  override fun modifyGsonBuilder(builder: GsonBuilder, buildInfo: BuildInfo): GsonBuilder {
    if (buildInfo.isDebug) {
      builder.setPrettyPrinting()
    }
    
    return builder
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(LocalDateTime::class.java, GsonLocalDateTimeAdapter())
  }
}
