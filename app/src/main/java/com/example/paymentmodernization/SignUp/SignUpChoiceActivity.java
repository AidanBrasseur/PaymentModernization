package com.example.paymentmodernization.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentmodernization.R;
/** An activity for choosing which user type the user wants to sign-up */
public class SignUpChoiceActivity extends AppCompatActivity {

  private Button driverButton;
  private Button supplierButton;
  private Button smallBusinessButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup_choice);
    driverButton = findViewById(R.id.driverButton);
    supplierButton = findViewById(R.id.supplier);
    smallBusinessButton = findViewById(R.id.businessOwner);
    driverButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(SignUpChoiceActivity.this, DriverDriverSignUp.class);
            startActivity(intent);
          }
        });
    supplierButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(SignUpChoiceActivity.this, CompanySignUp.class);
            intent.putExtra("type", "SUPPLIER");
            startActivity(intent);
          }
        });
    smallBusinessButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(SignUpChoiceActivity.this, CompanySignUp.class);
            intent.putExtra("type", "SMALL_BUSINESS");
            startActivity(intent);
          }
        });
  }
}
