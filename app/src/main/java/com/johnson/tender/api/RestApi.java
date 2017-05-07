package com.johnson.tender.api;

import com.johnson.tender.entity.Company;
import com.johnson.tender.entity.ListResponse;
import com.johnson.tender.entity.ObjectResponse;
import com.johnson.tender.entity.Project;
import com.johnson.tender.entity.Staff;
import com.johnson.tender.entity.User;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Johnson on 2017/5/3.
 */

public interface RestApi {

  @GET("/company/all")
  Observable<ListResponse<Company>> getCompanies(@Query("offset") int offset,
                                                 @Query("page_size") int pageSize);

  @GET("/company/query")
  Observable<ListResponse<Company>> queryCompanies(@QueryMap Map<String, String> queries,
                                                   @Query("orders") String orders,
                                                   @Query("offset") int offset,
                                                   @Query("page_size") int pageSize);

  @GET("/staff/query")
  Observable<ListResponse<Staff>> queryStaff(@QueryMap Map<String, String> queries,
                                             @Query("orders") String orders,
                                             @Query("offset") int offset,
                                             @Query("page_size") int pageSize);

  @GET("/project/query")
  Observable<ListResponse<Project>> queryProject(@QueryMap Map<String, String> queries,
                                                 @Query("orders") String orders,
                                                 @Query("offset") int offset,
                                                 @Query("page_size") int pageSize);

  @GET("user/login")
  Observable<ObjectResponse<User>> login(@Query("email") String email,
                                         @Query("password") String password);
}
