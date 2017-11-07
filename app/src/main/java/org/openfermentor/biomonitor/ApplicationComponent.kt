package org.openfermentor.biomonitor

import com.xmartlabs.bigbang.core.controller.SessionController
import com.xmartlabs.bigbang.core.controller.SharedPreferencesController
import com.xmartlabs.bigbang.core.module.CoreAndroidModule
import com.xmartlabs.bigbang.core.module.GsonModule
import com.xmartlabs.bigbang.core.module.OkHttpModule
import com.xmartlabs.bigbang.core.module.PicassoModule
import com.xmartlabs.bigbang.core.module.SessionInterceptor
import com.xmartlabs.bigbang.core.providers.AccessTokenProvider
import com.xmartlabs.bigbang.retrofit.module.RestServiceModule
import com.xmartlabs.bigbang.retrofit.module.ServiceGsonModule

import org.openfermentor.biomonitor.module.ControllerModule
import org.openfermentor.biomonitor.module.RestServiceModuleApi
import org.openfermentor.biomonitor.ui.StartActivity
import org.openfermentor.biomonitor.ui.experiment.view.ExperimentActivity
import org.openfermentor.biomonitor.ui.main.tabs.experiments.ExperimentListFragment
import org.openfermentor.biomonitor.ui.main.tabs.experiments.ExperimentListPresenter
import org.openfermentor.biomonitor.ui.main.tabs.system.SystemStatusFragment
import org.openfermentor.biomonitor.ui.main.tabs.system.SystemStatusPresenter
import org.openfermentor.biomonitor.ui.main.view.MainActivity
import org.openfermentor.biomonitor.ui.main.view.MainFragment
import org.openfermentor.biomonitor.ui.main.view.MainPresenter

import javax.inject.Singleton

import dagger.Component
import org.openfermentor.biomonitor.controller.experiment.ExperimentController
import org.openfermentor.biomonitor.controller.socket.SocketController
import org.openfermentor.biomonitor.controller.user.UserController
import org.openfermentor.biomonitor.ui.experiment.tabs.log.ExperimentLogFragment
import org.openfermentor.biomonitor.ui.experiment.tabs.log.ExperimentLogPresenter
import org.openfermentor.biomonitor.ui.experiment.tabs.overview.ExperimentOverviewFragment
import org.openfermentor.biomonitor.ui.experiment.tabs.overview.ExperimentOverviewPresenter
import org.openfermentor.biomonitor.ui.experiment.tabs.report.ExperimentReportFragment
import org.openfermentor.biomonitor.ui.experiment.tabs.report.ExperimentReportPresenter
import org.openfermentor.biomonitor.ui.experiment.view.ExperimentFragment
import org.openfermentor.biomonitor.ui.experiment.view.ExperimentPresenter
import org.openfermentor.biomonitor.ui.instruction.InstructionActivity
import org.openfermentor.biomonitor.ui.instruction.InstructionFragment
import org.openfermentor.biomonitor.ui.instruction.InstructionPresenter
import org.openfermentor.biomonitor.ui.login.LoginActivity
import org.openfermentor.biomonitor.ui.login.LoginFragment
import org.openfermentor.biomonitor.ui.login.LoginPresenter

@Singleton
@Component(modules = arrayOf(
    CoreAndroidModule::class,
    ControllerModule::class,
    GsonModule::class,
    ServiceGsonModule::class,
    OkHttpModule::class,
    PicassoModule::class,
    RestServiceModule::class,
    RestServiceModuleApi::class
))
interface ApplicationComponent {
  fun inject(app: App)

  fun inject(experimentActivity: ExperimentActivity)
  fun inject(instructionActivity: InstructionActivity)
  fun inject(loginActivity: LoginActivity)
  fun inject(mainActivity: MainActivity)
  fun inject(startActivity: StartActivity)

  fun inject(experimentFragment: ExperimentFragment)
  fun inject(experimentListFragment: ExperimentListFragment)
  fun inject(experimentLogFragment: ExperimentLogFragment)
  fun inject(experimentOverviewFragment: ExperimentOverviewFragment)
  fun inject(experimentReportFragment: ExperimentReportFragment)
  fun inject(instructionFragment: InstructionFragment)
  fun inject(loginFragment: LoginFragment)
  fun inject(mainFragment: MainFragment)
  fun inject(systemStatusFragment: SystemStatusFragment)

  fun inject(experimentPresenter: ExperimentPresenter)
  fun inject(experimentListPresenter: ExperimentListPresenter)
  fun inject(experimentLogPresenter: ExperimentLogPresenter)
  fun inject(experimentOverviewPresenter: ExperimentOverviewPresenter)
  fun inject(experimentReportPresenter: ExperimentReportPresenter)
  fun inject(instructionPresenter: InstructionPresenter)
  fun inject(loginPresenter: LoginPresenter)
  fun inject(mainPresenter: MainPresenter)
  fun inject(systemStatusPresenter: SystemStatusPresenter)

  fun inject(experimentController: ExperimentController)
  fun inject(sessionController: SessionController)
  fun inject(sharedPreferencesController: SharedPreferencesController)
  fun inject(socketController: SocketController)
  fun inject(userController: UserController)

  fun inject(accessTokenProvider: AccessTokenProvider)
  fun inject(sessionInterceptor: SessionInterceptor)
}
