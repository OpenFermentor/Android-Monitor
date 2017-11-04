package org.openfermentor.biomonitor.module

import android.content.Context
import org.openfermentor.biomonitor.App
import org.openfermentor.biomonitor.R
import okhttp3.HttpUrl
import com.xmartlabs.bigbang.retrofit.module.RestServiceModule as CoreRestServiceModule

class RestServiceModule : CoreRestServiceModule() {
  companion object {
    private val BASE_URL = App.context.resources.getString(R.string.base_url)
  }

  override fun provideBaseUrl(context: Context) = HttpUrl.parse(BASE_URL)!!
}
