package com.example.paymentmodernization.Login;

import android.text.TextUtils;
import android.util.Base64;

import com.example.paymentmodernization.PaymentModernizationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginInteractor {

  interface OnLoginFinishedListener {
    void onUsernameError(String message);

    void onPasswordError(String message);

    void onSuccess();

    void onFail();
  }

  public void login(
      final String username, final String password, final OnLoginFinishedListener listener) {

    if (TextUtils.isEmpty(username)) {
      listener.onUsernameError("Cannot have empty username");
      return;
    }
    if (TextUtils.isEmpty(password)) {
      listener.onPasswordError("Cannot have empty password");
      return;
    }
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
                if (loginAuthorization.getIsValid().equals("true")) {
                  listener.onSuccess();
                } else {
                  listener.onFail();
                }
              }
            }

            @Override
            public void onFailure(Call<LoginAuthorization> call, Throwable t) {}
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
