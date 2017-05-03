package com.johnson.tender;

import android.app.Application;

import com.johnson.tender.dagger.component.DaggerNetworkComponent;
import com.johnson.tender.dagger.component.NetworkComponent;
import com.johnson.tender.dagger.module.AppModule;
import com.johnson.tender.dagger.module.NetworkModule;

/**
 * Created by Johnson on 2017/5/3.
 */

public class App extends Application {
  private NetworkComponent networkComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    networkComponent = DaggerNetworkComponent.builder().appModule(new AppModule(this))
        .networkModule(new NetworkModule(BuildConfig.BASE_URL)).build();
  }

  public NetworkComponent getNetworkComponent() {
    return networkComponent;
  }
}
