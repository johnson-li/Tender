package com.johnson.tender.api;

import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.ListResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Johnson on 2017/5/3.
 */

public interface RestApi {

  @GET("/company/all")
  Observable<ListResponse<Company>> getCompanies(@Query("offset") int offset,
                                                 @Query("page_size") int pageSize);
}
