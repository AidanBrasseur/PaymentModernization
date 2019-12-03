package com.example.paymentmodernization.SignUp;

/** An interface for the methods necessary for company sign-up functionality */
public interface CompanySignUpView {
  /** Shows current loading progress */
  void showProgress();
  /** Hides current loading progress */
  void hideProgress();
  /**
   * Sets error message given an error with the username
   *
   * @param message the message for the error
   */
  void setUsernameError(String message);
  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  void setPasswordError(String message);
  /** switches current screen to the login screen */
  void switchToLogin();
  /** sends message to user indicating that their sign-up attempt was invalid */
  void sendInvalidSignUpMessage();
  /**
   * Sets error message given an error with the full name
   *
   * @param message the message for the error
   */
  void setFullNameError(String message);
  /**
   * Sets error message given an error with the bank account number
   *
   * @param message the message for the error
   */
  void setBankNumError(String message);
  /**
   * Sets error message given an error with the street address
   *
   * @param message the message for the error
   */
  void setStreetAddressError(String message);
  /**
   * Sets error message given an error with the city
   *
   * @param message the message for the error
   */
  void setCityError(String message);
  /**
   * Sets error message given an error with the country
   *
   * @param message the message for the error
   */
  void setCountryError(String message);
  /**
   * Sets error message given an error with the region
   *
   * @param message the message for the error
   */
  void setRegionError(String message);
  /**
   * Sets error message given an error with the postal code
   *
   * @param message the message for the error
   */
  void setPostalCodeError(String message);
}
