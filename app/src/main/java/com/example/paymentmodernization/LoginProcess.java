package com.example.paymentmodernization;

import android.util.Base64;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Class that handles authentication w/ login credentials and retrieves user information. */
public class LoginProcess {

  public void login(final String username, String password) {

    try {
      String authorizationString =
          Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);

      Retrofit retrofit =
          new Retrofit.Builder()
              .baseUrl("https://payment-modernization.herokuapp.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();
      PaymentModernizationAPI paymentModernizationApi =
          retrofit.create(PaymentModernizationAPI.class);

      final Call<LoginAuthorization> call =
          paymentModernizationApi.getLoginAuthorization(authorizationString);

      call.enqueue(
          new Callback<LoginAuthorization>() {

            @Override
            public void onResponse(
                Call<LoginAuthorization> call, Response<LoginAuthorization> response) {
              if (!response.isSuccessful()) {
                System.out.println(response.code());
              } else {
                LoginAuthorization loginAuthorization = response.body();
              }
            }

            @Override
            public void onFailure(Call<LoginAuthorization> call, Throwable t) {
              System.out.println("OnFailure" + t.getMessage());
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logout() {
    // TODO: revoke authentication
  }
}
