package com.example.paymentmodernization;

import com.example.paymentmodernization.Login.LoginAuthorization;
import com.example.paymentmodernization.HomePage.Invoices;

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

  /** Gets invoices of the user */
  @GET("invoices")
  Call<Invoices> getInvoices(@Header("Authorization") String authorizationString);

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

  /**
   * Signs up a new company with given username, password, name, type, bank information, and address
   *
   * @param username username input
   * @param password password input
   * @param name the name of the user
   * @param type the user type
   * @param accountNum the company bank account number
   * @param cardNum the company card number
   * @param bank the bank associated with the company
   * @param streetAddress the street address of the company
   * @param city the city the company is located
   * @param region the region the company is located
   * @param country the country the company is located
   * @param postalCode the postal code of the company
   */
  @FormUrlEncoded
  @POST("signup-company")
  Call<String> insertNewCompany(
      @Field("username") String username,
      @Field("password") String password,
      @Field("name") String name,
      @Field("type") String type,
      @Field("accountNum") String accountNum,
      @Field("cardNum") String cardNum,
      @Field("bank") String bank,
      @Field("streetAddress") String streetAddress,
      @Field("city") String city,
      @Field("region") String region,
      @Field("country") String country,
      @Field("postalCode") String postalCode);
}
