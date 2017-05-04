package com.johnson.tender.dagger.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.johnson.tender.Preferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Johnson on 2017/5/3.
 */

@Module
public class AppModule {
  Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @Singleton
  Application provideApplication() {
    return application;
  }

  @Provides
  @Singleton
  SharedPreferences provideSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }

  @Provides
  @Singleton
  Preferences providePreferences(SharedPreferences sharedPreferences, Gson gson) {
    return new Preferences(sharedPreferences, gson);
  }
}
