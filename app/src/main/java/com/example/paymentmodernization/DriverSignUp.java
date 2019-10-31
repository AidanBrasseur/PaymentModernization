package com.example.paymentmodernization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DriverSignUp extends AppCompatActivity {

  private EditText fullNameText;
  private EditText usernameText;
  private EditText passwordText;
  private Button createAccountButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_driver_sign_up);
    fullNameText = findViewById(R.id.fullName);
    usernameText = findViewById(R.id.username);
    passwordText = findViewById(R.id.password);
    createAccountButton = findViewById(R.id.createAccount);

    createAccountButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            insertUser(
                usernameText.getText().toString(),
                passwordText.getText().toString(),
                fullNameText.getText().toString());
          }
        });
  }

  private void insertUser(String username, String password, String fullName) {
    try {
      String encodedUrl =
          "username="
              + username
              + "&password="
              + password
              + "&name="
              + fullName
              + "&type=DELIVERY_PERSON";

      Retrofit retrofit =
          new Retrofit.Builder()
              .baseUrl("https://payment-modernization.herokuapp.com/")
              .addConverterFactory(ScalarsConverterFactory.create())
              .build();
      PaymentModernizationApi paymentModernizationApi =
          retrofit.create(PaymentModernizationApi.class);

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
                  Intent intent = new Intent(DriverSignUp.this, HomePage.class);
                  startActivity(intent);
                } else {
                  usernameText.setText("");
                  Toast.makeText(
                          getApplicationContext(), "Username already taken", Toast.LENGTH_LONG)
                      .show();
                }
              }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
              System.out.println("************" + t.getMessage());
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
