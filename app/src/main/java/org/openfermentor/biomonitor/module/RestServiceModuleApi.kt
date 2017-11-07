package org.openfermentor.biomonitor.module

import com.xmartlabs.bigbang.core.providers.AccessTokenProvider
import dagger.Module
import dagger.Provides
import org.openfermentor.biomonitor.service.ExperimentService
import org.openfermentor.biomonitor.service.UserService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RestServiceModuleApi {
  @Provides
  @Singleton
  internal fun provideAccessTokenProvider() = AccessTokenProvider()
  
  @Provides
  @Singleton
  internal fun provideExperimentService(retrofit: Retrofit) = retrofit.create(ExperimentService::class.java)
  
  @Provides
  @Singleton
  internal fun provideUserService(retrofit: Retrofit) = retrofit.create(UserService::class.java)
}
