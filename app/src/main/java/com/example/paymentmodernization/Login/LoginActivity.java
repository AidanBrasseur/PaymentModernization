package com.example.paymentmodernization.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.paymentmodernization.HomePage;
import com.example.paymentmodernization.R;
import com.example.paymentmodernization.SignUp.SignUpChoiceActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
  private EditText usernameField;
  private EditText passwordField;
  private Button signInButton;
  private Button signUpButton;
  private LoginPresenter presenter;
  private ProgressBar progressBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    usernameField = findViewById(R.id.username);
    passwordField = findViewById(R.id.password);
    signInButton = findViewById(R.id.login);
    signUpButton = findViewById(R.id.signUp);
    progressBar = findViewById(R.id.loading);

    presenter = new LoginPresenter(this, new LoginInteractor());
    signUpButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            switchToSignUpScreen();
          }
        });
    signInButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            authorizeLogin();
          }
        });
  }

  @Override
  protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @Override
  public void switchToHomeScreen() {
    Intent intent = new Intent(LoginActivity.this, HomePage.class);
    startActivity(intent);
  }

  @Override
  public void switchToSignUpScreen() {
    Intent intent = new Intent(LoginActivity.this, SignUpChoiceActivity.class);
    startActivity(intent);
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
    usernameField.setError(message);
  }

  @Override
  public void setPasswordError(String message) {
    passwordField.setError(message);
  }

  @Override
  public void sendInvalidAuthorizationMessage() {
    progressBar.setVisibility(View.GONE);
    usernameField.setText("");
    passwordField.setText("");
    Toast toast =
        Toast.makeText(
            getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_LONG);

    toast.show();
  }

  private void authorizeLogin() {
    presenter.loginAuthorization(
        usernameField.getText().toString(), passwordField.getText().toString());
  }
}
