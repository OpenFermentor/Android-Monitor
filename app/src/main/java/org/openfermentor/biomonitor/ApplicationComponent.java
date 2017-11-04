package org.openfermentor.biomonitor;

import com.xmartlabs.bigbang.core.controller.SessionController;
import com.xmartlabs.bigbang.core.controller.SharedPreferencesController;
import com.xmartlabs.bigbang.core.module.CoreAndroidModule;
import com.xmartlabs.bigbang.core.module.GsonModule;
import com.xmartlabs.bigbang.core.module.OkHttpModule;
import com.xmartlabs.bigbang.core.module.PicassoModule;
import com.xmartlabs.bigbang.core.module.SessionInterceptor;
import com.xmartlabs.bigbang.core.providers.AccessTokenProvider;
import com.xmartlabs.bigbang.retrofit.module.RestServiceModule;
import com.xmartlabs.bigbang.retrofit.module.ServiceGsonModule;
import org.openfermentor.biomonitor.controller.AuthController;
import org.openfermentor.biomonitor.module.ControllerModule;
import org.openfermentor.biomonitor.module.RestServiceModuleApi;
import org.openfermentor.biomonitor.ui.StartActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    CoreAndroidModule.class,
    ControllerModule.class,
    GsonModule.class,
    ServiceGsonModule.class,
    OkHttpModule.class,
    PicassoModule.class,
    RestServiceModule.class,
    RestServiceModuleApi.class,
})
public interface ApplicationComponent {
  void inject(App app);

  void inject(StartActivity startActivity);

  void inject(AuthController authController);
  void inject(SessionController sessionController);
  void inject(SharedPreferencesController sharedPreferencesController);

  void inject(AccessTokenProvider accessTokenProvider);
  void inject(SessionInterceptor sessionInterceptor);
}
