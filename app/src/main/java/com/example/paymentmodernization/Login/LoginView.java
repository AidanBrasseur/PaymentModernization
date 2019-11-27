package com.example.paymentmodernization.Login;

/** An interface for the methods necessary for login functionality */
public interface LoginView {

  /** switches current screen to the homescreen */
  void switchToHomeScreen(UserInformation userInformation);
  /** switches current screen to the sign-up choice page */
  void switchToSignUpScreen();
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

  /** sends message to user indicating that their login attempt was invalid */
  void sendInvalidAuthorizationMessage();
}
