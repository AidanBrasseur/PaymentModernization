package com.example.paymentmodernization;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
  private EditText usernameField;
  private EditText passwordField;
  private Button signInButton;
  private Button signUpButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    usernameField = findViewById(R.id.username);
    passwordField = findViewById(R.id.password);
    signInButton = findViewById(R.id.login);
    signUpButton = findViewById(R.id.signUp);
    signUpButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
          }
        });
    signInButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            System.out.println("**********************");
            authorizeLogin(usernameField.getText().toString(), passwordField.getText().toString());
          }
        });
  }

  void authorizeLogin(String username, final String password) {
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
                  Intent intent = new Intent(LoginActivity.this, HomePage.class);
                  startActivity(intent);
                } else {
                  usernameField.setText("");
                  passwordField.setText("");
                  Toast.makeText(
                          getApplicationContext(),
                          "Incorrect Username or Password",
                          Toast.LENGTH_LONG)
                      .show();
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
