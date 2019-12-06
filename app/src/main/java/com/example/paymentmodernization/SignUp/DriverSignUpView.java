package com.example.paymentmodernization.SignUp;

/** An interface for the methods necessary for sign-up functionality */
interface DriverSignUpView {
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
}
