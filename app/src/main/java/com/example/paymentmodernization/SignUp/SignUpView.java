package com.example.paymentmodernization.SignUp;

public interface SignUpView {
  void showProgress();

  void hideProgress();

  void setUsernameError(String message);

  void setPasswordError(String message);

  void switchToLogin();

  void sendInvalidSignUpMessage();
}
