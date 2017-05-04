package com.johnson.tender;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.johnson.tender.entity.User;

/**
 * Created by Johnson on 2017/5/4.
 */

public class Preferences {
  SharedPreferences sharedPreferences;
  Gson gson;
  private String USER_ATTR = "user";

  public Preferences(SharedPreferences sharedPreferences, Gson gson) {
    this.sharedPreferences = sharedPreferences;
    this.gson = gson;
  }

  public User getUser() {
    String userStr = sharedPreferences.getString(USER_ATTR, null);
    if (userStr != null) {
      return gson.fromJson(userStr, User.class);
    }
    return null;
  }

  public void setUser(User user) {
    sharedPreferences.edit().putString(USER_ATTR, gson.toJson(user)).apply();
  }
}
