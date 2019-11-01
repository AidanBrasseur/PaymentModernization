package com.example.paymentmodernization.SignUp;

public class SignUpPresenter implements SignUpInteractor.OnSignUpFinishedListener {
  private SignUpView signUpView;
  private SignUpInteractor signUpInteractor;

  SignUpPresenter(SignUpView signUpView, SignUpInteractor signUpInteractor) {
    this.signUpView = signUpView;
    this.signUpInteractor = signUpInteractor;
  }

  public void signUpUser(String username, String password, String fullName) {
    if (signUpView != null) {
      signUpView.showProgress();
    }
    signUpInteractor.signUpUser(username, password, fullName, this);
  }

  public void onDestroy() {
    signUpView = null;
  }

  @Override
  public void onUsernameError(String message) {
    if (signUpView != null) {
      signUpView.setUsernameError(message);
      signUpView.hideProgress();
    }
  }

  @Override
  public void onPasswordError(String message) {
    if (signUpView != null) {
      signUpView.setPasswordError(message);
      signUpView.hideProgress();
    }
  }

  @Override
  public void onSuccess() {
    if (signUpView != null) {
      signUpView.switchToLogin();
    }
  }

  @Override
  public void onFail() {
    if (signUpView != null) {
      signUpView.sendInvalidSignUpMessage();
    }
  }
}
