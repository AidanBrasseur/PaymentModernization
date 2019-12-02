package com.example.paymentmodernization;

import com.example.paymentmodernization.ui.home.Invoice;
import com.example.paymentmodernization.ui.home.Invoices;
import com.example.paymentmodernization.Login.UserInformation;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/** An interface dictating the get and post methods corresponding to API calls */
public interface PaymentModernizationAPI {

  /** Gets information about the validity of the login credentials */
  @GET("login")
  Call<UserInformation> getLoginAuthorization(@Header("Authorization") String authorizationString);

  /** Gets invoices of the user */
  @GET("invoices")
  Call<Invoices> getInvoices(@Header("Authorization") String authorizationString);


  /**
   *
   * @param invoiceId
   * @param authToken
   * @return Invoice object with details of specific invoice
   */
  @GET("invoices/{invoiceId}")
  Call<Invoice> getInvoice(
      @Path("invoiceId") String invoiceId,
      @Header("Authorization") String authToken
  );

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

  /** updates status for particular invoice */
  @FormUrlEncoded
  @POST("invoices/update-status/{invoiceId}")
  Call<String> updateStatus(
      @Path("invoiceId") String invoiceId,
      @Header("Authorization") String authToken,
      @Field("newStatus") String newStatus);

  /**
   * Signs up a new company with given username, password, name, type, bank information, and address
   *
   * @param username username input
   * @param password password input
   * @param name the name of the user
   * @param type the user type
   * @param accountNum the company bank account number
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
      @Field("bank") String bank,
      @Field("streetAddress") String streetAddress,
      @Field("city") String city,
      @Field("region") String region,
      @Field("country") String country,
      @Field("postalCode") String postalCode);
  /**
   * Creates a new invoice
   *
   * @param authToken the authorization token of the user
   * @param business the recipient of the invoice
   * @param deliveryPerson the delivery person for the invoice
   * @param invoiceDate the creation date of the invoice
   * @param dueDate the estimated delivery date of the invoice
   * @param items the order items associated with invoice
   */
  @FormUrlEncoded
  @POST("invoices/new-invoice")
  Call<String> createInvoice(
      @Header("Authorization") String authToken,
      @Field("business") String business,
      @Field("deliveryPerson") String deliveryPerson,
      @Field("invoiceDate") String invoiceDate,
      @Field("dueDate") String dueDate,
      @Field("items") String items);
}
