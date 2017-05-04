package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Johnson on 2017/5/4.
 */

public class User {

  @SerializedName("ID")
  long id;
  @SerializedName("Gender")
  String gender;
  @SerializedName("Email")
  String email;
  @SerializedName("Type")
  int type;
  @SerializedName("Token")
  String token;
  @SerializedName("Name")
  String name;

  public long getId() {
    return id;
  }

  public String getGender() {
    return gender;
  }

  public String getEmail() {
    return email;
  }

  public int getType() {
    return type;
  }

  public String getToken() {
    return token;
  }

  public String getName() {
    return name;
  }
}
