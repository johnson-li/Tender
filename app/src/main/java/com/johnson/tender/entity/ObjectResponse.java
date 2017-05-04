package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Johnson on 2017/5/4.
 */

public class ObjectResponse<T> implements Response {
  @SerializedName("rc")
  int responseCode;
  String error;
  T content;

  public T getContent() {
    return content;
  }

  @Override
  public boolean isSuccessful() {
    return responseCode == 1;
  }

  @Override
  public String error() {
    return error;
  }
}
