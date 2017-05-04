package com.johnson.tender.api;

import com.johnson.tender.Preferences;
import com.johnson.tender.entity.User;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Johnson on 2017/5/4.
 */

public class LoginInterceptor implements Interceptor {

  Preferences preferences;

  public LoginInterceptor(Preferences preferences) {
    this.preferences = preferences;
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();
    User user = preferences.getUser();
    if (user != null) {
      request = request.newBuilder().addHeader("token", user.getToken()).build();
    }
    return chain.proceed(request);
  }
}
