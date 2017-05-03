package com.johnson.tender.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Johnson on 2017/5/3.
 */

public class ListResponse<T> {

  List<T> content;

  @SerializedName("rc")
  int responseCode;

  public List<T> getContent() {
    return content;
  }

  public int getResponseCode() {
    return responseCode;
  }
}
