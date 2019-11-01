package com.example.paymentmodernization.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.paymentmodernization.Login.LoginActivity;
import com.example.paymentmodernization.R;

public class DriverSignUp extends AppCompatActivity implements SignUpView {

  private EditText fullNameText;
  private EditText usernameText;
  private EditText passwordText;
  private Button createAccountButton;
  private SignUpPresenter presenter;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_driver_sign_up);
    fullNameText = findViewById(R.id.fullName);
    usernameText = findViewById(R.id.username);
    passwordText = findViewById(R.id.password);
    createAccountButton = findViewById(R.id.createAccount);
    progressBar = findViewById(R.id.progressBar);
    presenter = new SignUpPresenter(this, new SignUpInteractor());
    createAccountButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            signUpUser();
          }
        });
  }

  @Override
  protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  void signUpUser() {
    presenter.signUpUser(
        usernameText.getText().toString(),
        passwordText.getText().toString(),
        fullNameText.getText().toString());
  }

  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override
  public void setUsernameError(String message) {
    usernameText.setError(message);
  }

  @Override
  public void setPasswordError(String message) {
    passwordText.setError(message);
  }

  @Override
  public void switchToLogin() {
    Intent intent = new Intent(DriverSignUp.this, LoginActivity.class);
    startActivity(intent);
  }

  @Override
  public void sendInvalidSignUpMessage() {
    usernameText.setText("");
    progressBar.setVisibility(View.GONE);
    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
  }
}
