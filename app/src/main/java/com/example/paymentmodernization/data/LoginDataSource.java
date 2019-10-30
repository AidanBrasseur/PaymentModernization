package com.example.paymentmodernization.data;

import android.util.Base64;

import com.example.paymentmodernization.LoginAuthorization;
import com.example.paymentmodernization.PaymentModernizationApi;
import com.example.paymentmodernization.data.model.LoggedInUser;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/** Class that handles authentication w/ login credentials and retrieves user information. */
public class LoginDataSource {

  public Result<LoggedInUser> login(String username, String password) {

    try {

      String authorizationString =
          Base64.encodeToString((username + ":" + password).getBytes(), Base64.NO_WRAP);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://payment-modernization.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PaymentModernizationApi paymentModernizationApi = retrofit.create(PaymentModernizationApi.class);

        Call<LoginAuthorization> call = paymentModernizationApi.getLoginAuthorization(authorizationString);

      call.enqueue(
          new Callback<LoginAuthorization>() {
            @Override
            public void onResponse(
                Call<LoginAuthorization> call, Response<LoginAuthorization> response) {
              if (!response.isSuccessful()) {
                System.out.println(response.code());
              } else {
                LoginAuthorization login = response.body();
                System.out.println(login.getIsValid());
              }
            }

            @Override
            public void onFailure(Call<LoginAuthorization> call, Throwable t) {
              System.out.println("@@@@@@@@@@@@@ OnFailure" + t.getMessage());
            }
          });

      LoggedInUser newUser = new LoggedInUser(username, "temp name");
      return new Result.Success<>(newUser);

    } catch (Exception e) {
      return new Result.Error(new IOException("Error logging in", e));
    }
  }

  public void logout() {
    // TODO: revoke authentication
  }
}
