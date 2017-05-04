package com.johnson.tender.dagger.component;

import com.johnson.tender.CompanyFragment;
import com.johnson.tender.LoginActivity;
import com.johnson.tender.MainActivity;
import com.johnson.tender.dagger.module.AppModule;
import com.johnson.tender.dagger.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Johnson on 2017/5/3.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface NetworkComponent {

  void inject(MainActivity activity);

  void inject(LoginActivity activity);

  void inject(CompanyFragment fragment);
}
