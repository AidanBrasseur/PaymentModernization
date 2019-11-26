package com.example.paymentmodernization.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentmodernization.NavDrawer;
import com.example.paymentmodernization.R;
import com.example.paymentmodernization.SignUp.SignUpChoiceActivity;

/** Activity to display login page. Implements LoginView. */
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

  /** switches current screen to the homescreen */
  @Override
  public void switchToHomeScreen(String authToken, String userType, String fullName) {
    Intent intent = new Intent(LoginActivity.this, NavDrawer.class);
    intent.putExtra("authToken", authToken);
    intent.putExtra("userType", userType);
    intent.putExtra("username", usernameField.getText().toString());
    intent.putExtra("fullName", fullName);
    startActivity(intent);
  }
  /** switches current screen to the sign-up choice screen */
  @Override
  public void switchToSignUpScreen() {
    Intent intent = new Intent(LoginActivity.this, SignUpChoiceActivity.class);
    startActivity(intent);
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
    usernameField.setError(message);
  }
  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  @Override
  public void setPasswordError(String message) {
    passwordField.setError(message);
  }
  /** sends message to user indicating that their login attempt was invalid */
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
  /**
   * Authorizes the credentials of the login credentials using the username and password fields text
   */
  private void authorizeLogin() {
    presenter.loginAuthorization(
        usernameField.getText().toString(), passwordField.getText().toString());
  }
}
