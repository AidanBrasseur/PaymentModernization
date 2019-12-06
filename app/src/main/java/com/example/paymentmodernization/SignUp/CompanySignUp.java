package com.example.paymentmodernization.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentmodernization.Login.LoginActivity;
import com.example.paymentmodernization.R;

/** Activity to display company sign-up page. Implements CompanySignUpView. */
public class CompanySignUp extends AppCompatActivity implements CompanySignUpView {

  private EditText fullNameText;
  private EditText usernameText;
  private EditText passwordText;
  private EditText accountNum;
  private Spinner bankChoice;
  private EditText streetAddress;
  private EditText city;
  private EditText region;
  private EditText country;
  private EditText postalCode;
  private Button createAccountButton;
  private CompanySignUpPresenter presenter;
  private ProgressBar progressBar;
  private String companyType = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_company_sign_up);
    companyType = getIntent().getStringExtra("type");
    fullNameText = findViewById(R.id.fullName);
    usernameText = findViewById(R.id.username);
    passwordText = findViewById(R.id.password);
    accountNum = findViewById(R.id.accountNum);
    bankChoice = findViewById(R.id.bankChoice);
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
            this, R.array.banks_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    bankChoice.setAdapter(adapter);
    streetAddress = findViewById(R.id.streetAddress);
    city = findViewById(R.id.city);
    region = findViewById(R.id.region);
    country = findViewById(R.id.country);
    postalCode = findViewById(R.id.postalCode);
    createAccountButton = findViewById(R.id.createAccount);
    progressBar = findViewById(R.id.progressBar);
    presenter = new CompanySignUpPresenter(this, new CompanySignUpInteractor());
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
  /** Signs up new company using the input from the displayed text fields */
  void signUpUser() {
    presenter.signUpUser(
        usernameText.getText().toString(),
        passwordText.getText().toString(),
        fullNameText.getText().toString(),
        companyType,
        accountNum.getText().toString(),
        bankChoice.getSelectedItem().toString(),
        streetAddress.getText().toString(),
        city.getText().toString(),
        region.getText().toString(),
        country.getText().toString(),
        postalCode.getText().toString());
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
    Intent intent = new Intent(CompanySignUp.this, LoginActivity.class);
    startActivity(intent);
  }
  /** sends message to user indicating that their sign-up attempt was invalid */
  @Override
  public void sendInvalidSignUpMessage() {
    usernameText.setText("");
    progressBar.setVisibility(View.GONE);
    Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_LONG).show();
  }
  /**
   * Sets error message given an error with the full name
   *
   * @param message the message for the error
   */
  @Override
  public void setFullNameError(String message) {
    fullNameText.setError(message);
  }
  /**
   * Sets error message given an error with the bank account number
   *
   * @param message the message for the error
   */
  @Override
  public void setBankNumError(String message) {
    accountNum.setError(message);
  }
  /**
   * Sets error message given an error with the street adresss
   *
   * @param message the message for the error
   */
  @Override
  public void setStreetAddressError(String message) {
    streetAddress.setError(message);
  }
  /**
   * Sets error message given an error with the city
   *
   * @param message the message for the error
   */
  @Override
  public void setCityError(String message) {
    city.setError(message);
  }
  /**
   * Sets error message given an error with the country
   *
   * @param message the message for the error
   */
  @Override
  public void setCountryError(String message) {
    country.setError(message);
  }
  /**
   * Sets error message given an error with the region
   *
   * @param message the message for the error
   */
  @Override
  public void setRegionError(String message) {
    region.setError(message);
  }
  /**
   * Sets error message given an error with the postal code
   *
   * @param message the message for the error
   */
  @Override
  public void setPostalCodeError(String message) {
    postalCode.setError(message);
  }
}
