package com.example.paymentmodernization;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PaymentModernizationApi {

  @GET("login")
  Call<LoginAuthorization> getLoginAuthorization(
      @Header("Authorization") String authorizationString);

  @FormUrlEncoded
  @POST("signup-user")
  Call<String> insertNewUser(
      @Field("username") String username,
      @Field("password") String password,
      @Field("name") String name,
      @Field("type") String type);
}
