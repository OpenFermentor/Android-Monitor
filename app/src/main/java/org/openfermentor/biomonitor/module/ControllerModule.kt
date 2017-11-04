package org.openfermentor.biomonitor.module

import com.xmartlabs.bigbang.core.controller.SessionController
import org.openfermentor.biomonitor.controller.AuthController
import org.openfermentor.biomonitor.model.Session
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ControllerModule {
  @Provides
  @Singleton
  internal fun provideAuthController() = AuthController()

  @Provides
  @Singleton
  internal fun provideSessionController() = SessionController(Session::class.java)
}
