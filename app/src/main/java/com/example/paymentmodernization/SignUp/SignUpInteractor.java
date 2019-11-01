package com.example.paymentmodernization.SignUp;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.paymentmodernization.PaymentModernizationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignUpInteractor {

  interface OnSignUpFinishedListener {
    void onUsernameError(String message);

    void onPasswordError(String message);

    void onSuccess();

    void onFail();
  }

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
            public void onFailure(Call<String> call, Throwable t) {}
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
