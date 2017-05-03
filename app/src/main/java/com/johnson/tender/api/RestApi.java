package com.johnson.tender.api;

import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.ListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Johnson on 2017/5/3.
 */

public interface RestApi {

  @GET("/company/all")
  Call<ListResponse<Company>> getCompanies();
}
