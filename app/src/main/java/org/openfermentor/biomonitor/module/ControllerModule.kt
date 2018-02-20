package org.openfermentor.biomonitor.module

import com.xmartlabs.bigbang.core.controller.SessionController
import org.openfermentor.biomonitor.model.Session
import dagger.Module
import dagger.Provides
import org.openfermentor.biomonitor.controller.experiment.ExperimentController
import org.openfermentor.biomonitor.controller.user.UserController
import javax.inject.Singleton

@Module
class ControllerModule {
  @Provides
  @Singleton
  internal fun provideExperimentController() = ExperimentController()
  
  @Provides
  @Singleton
  internal fun provideSessionController() = SessionController(Session::class.java)
  
  @Provides
  @Singleton
  internal fun provideUserController() = UserController()
}
