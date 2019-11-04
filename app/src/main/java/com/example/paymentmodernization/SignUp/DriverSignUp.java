package com.example.paymentmodernization.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentmodernization.Login.LoginActivity;
import com.example.paymentmodernization.R;

/** Activity to display driver sign-up page. Implements SignUpView. */
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
  /** Signs up new user with username, password, and fullName fields text */
  void signUpUser() {
    presenter.signUpUser(
        usernameText.getText().toString(),
        passwordText.getText().toString(),
        fullNameText.getText().toString());
  }
  /** Shows current loading progress */
  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }
  /** Hides current loading progress */
  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }
  /**
   * Sets error message given an error with the username
   *
   * @param message the message for the error
   */
  @Override
  public void setUsernameError(String message) {
    usernameText.setError(message);
  }
  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  @Override
  public void setPasswordError(String message) {
    passwordText.setError(message);
  }

  /** switches current screen to the login screen */
  @Override
  public void switchToLogin() {
    Intent intent = new Intent(DriverSignUp.this, LoginActivity.class);
    startActivity(intent);
  }
  /** sends message to user indicating that their sign-up attempt was invalid */
  @Override
  public void sendInvalidSignUpMessage() {
    usernameText.setText("");
    progressBar.setVisibility(View.GONE);
    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
  }
}
