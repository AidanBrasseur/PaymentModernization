package com.example.paymentmodernization.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.paymentmodernization.R;

public class SignUpChoiceActivity extends AppCompatActivity {

  private Button driverButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup_choice);
    driverButton = findViewById(R.id.driverButton);
    driverButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(SignUpChoiceActivity.this, DriverSignUp.class);
            startActivity(intent);
          }
        });
  }
}