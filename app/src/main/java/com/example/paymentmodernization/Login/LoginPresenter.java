package com.example.paymentmodernization.Login;

public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener {

  private LoginView loginView;
  private LoginInteractor loginInteractor;

  LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
    this.loginView = loginView;
    this.loginInteractor = loginInteractor;
  }

  public void loginAuthorization(String username, String password) {
    if (loginView != null) {
      loginView.showProgress();
    }

    loginInteractor.login(username, password, this);
  }

  public void onDestroy() {
    loginView = null;
  }

  @Override
  public void onUsernameError(String message) {
    if (loginView != null) {
      loginView.setUsernameError(message);
      loginView.hideProgress();
    }
  }

  @Override
  public void onPasswordError(String message) {
    if (loginView != null) {
      loginView.setPasswordError(message);
      loginView.hideProgress();
    }
  }

  @Override
  public void onSuccess() {
    if (loginView != null) {
      loginView.switchToHomeScreen();
    }
  }

  @Override
  public void onFail() {
    if (loginView != null) {
      loginView.sendInvalidAuthorizationMessage();
    }
  }
}
