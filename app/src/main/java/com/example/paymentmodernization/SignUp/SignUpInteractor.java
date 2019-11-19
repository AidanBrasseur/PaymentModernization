package com.example.paymentmodernization.SignUp;

import android.text.TextUtils;

import com.example.paymentmodernization.PaymentModernizationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/** SignUpInteractor communicates with server to perform sign-up functionalities. */
public class SignUpInteractor {

  /**
   * Signs up a new user with given username, password, and fullName, calling the corresponding
   * method in listener.
   *
   * @param username the username input
   * @param password the password input
   * @param fullName the full name of the new user
   * @param listener an OnSignUpFinishedListener to signal sucess or failure during sign-up
   */
  public void signUpUser(
      String username, String password, String fullName, final OnSignUpFinishedListener listener) {
    if (TextUtils.isEmpty(username)) {
      listener.onUsernameError("Cannot have empty username");
      return;
    }
    if (TextUtils.isEmpty(password)) {
      listener.onPasswordError("Cannot have empty password");
      return;
    }
    try {

      Retrofit retrofit =
          new Retrofit.Builder()
              .baseUrl("https://payment-modernization.herokuapp.com/")
              .addConverterFactory(ScalarsConverterFactory.create())
              .build();
      PaymentModernizationAPI paymentModernizationApi =
          retrofit.create(PaymentModernizationAPI.class);

      final Call<String> call =
          paymentModernizationApi.insertNewUser(username, password, fullName, "DELIVERY_PERSON");

      call.enqueue(
          new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
              if (!response.isSuccessful()) {
                System.out.println(response.code());
              } else {
                String valid = response.body();
                if (valid.contains("true")) {
                  listener.onSuccess();
                } else {
                  listener.onFail();
                }
              }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
              // TODO: Handle failure of sign-up
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  // TODO: Fix documentation. Possibly move this out of this class and make two separate signup MVP
  // setups
  /**
   * Signs up a new user with given username, password, and fullName, calling the corresponding
   * method in listener.
   *
   * @param username the username input
   * @param password the password input
   * @param fullName the full name of the new user
   * @param listener an OnSignUpFinishedListener to signal sucess or failure during sign-up
   */
  public void signUpUser(
      String username,
      String password,
      String fullName,
      String userType,
      String accountNum,
      String cardNum,
      String bank,
      String streetAddress,
      String city,
      String region,
      String country,
      String postalCode,
      final OnSignUpFinishedListener listener) {
    if (TextUtils.isEmpty(username)) {
      listener.onUsernameError("Cannot have empty username");
      return;
    }
    if (TextUtils.isEmpty(password)) {
      listener.onPasswordError("Cannot have empty password");
      return;
    }
    try {

      Retrofit retrofit =
          new Retrofit.Builder()
              .baseUrl("https://payment-modernization.herokuapp.com/")
              .addConverterFactory(ScalarsConverterFactory.create())
              .build();
      PaymentModernizationAPI paymentModernizationApi =
          retrofit.create(PaymentModernizationAPI.class);

      final Call<String> call =
          paymentModernizationApi.insertNewCompany(
              username,
              password,
              fullName,
              userType,
              accountNum,
              cardNum,
              bank,
              streetAddress,
              city,
              region,
              country,
              postalCode);

      call.enqueue(
          new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
              if (!response.isSuccessful()) {
                System.out.println(response.code());
              } else {
                String valid = response.body();
                if (valid.contains("true")) {
                  listener.onSuccess();
                } else {
                  listener.onFail();
                }
              }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
              // TODO: Handle failure of sign-up
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /** OnSignUpFinishedListener is an interface outlines methods for use during sign-up. */
  interface OnSignUpFinishedListener {
    void onUsernameError(String message);

    void onPasswordError(String message);

    void onSuccess();

    void onFail();
  }
}
