package com.example.paymentmodernization;

import com.example.paymentmodernization.Login.LoginAuthorization;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
/** An interface dictating the get and post methods corresponding to API calls */
public interface PaymentModernizationAPI {

  /** Gets information about the validity of the login credentials */
  @GET("login")
  Call<LoginAuthorization> getLoginAuthorization(
      @Header("Authorization") String authorizationString);

  /**
   * Signs up a new user with given username, password, name and type
   *
   * @param username username input
   * @param password password input
   * @param name the name of the user
   * @param type the user type
   */
  @FormUrlEncoded
  @POST("signup-user")
  Call<String> insertNewUser(
      @Field("username") String username,
      @Field("password") String password,
      @Field("name") String name,
      @Field("type") String type);
}
