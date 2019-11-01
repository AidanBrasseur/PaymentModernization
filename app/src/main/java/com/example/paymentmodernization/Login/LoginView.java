package com.example.paymentmodernization.Login;

public interface LoginView {

  void switchToHomeScreen();

  void switchToSignUpScreen();

  void showProgress();

  void hideProgress();

  void setUsernameError(String message);

  void setPasswordError(String message);

  void sendInvalidAuthorizationMessage();
}
