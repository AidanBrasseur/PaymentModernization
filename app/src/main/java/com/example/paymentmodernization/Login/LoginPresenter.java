package com.example.paymentmodernization.Login;

/**
 * LoginPresenter retrieves data from the LoginInteractor and formats it for the LoginView.
 * Implements OnLoginFinishedListener to handle login events *
 */
public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener {

  /** The LoginView that this LoginPresenter formats information for */
  private LoginView loginView;
  /** The LoginInteractor that this LoginPresenter retrieves data from */
  private LoginInteractor loginInteractor;

  /**
   * Constructs a new LoginPresenter with given loginView and loginInteractor
   *
   * @param loginView the LoginView corresponding to this LoginPresenter
   * @param loginInteractor the LoginInteractor corresponding to this LoginPresenter
   */
  LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
    this.loginView = loginView;
    this.loginInteractor = loginInteractor;
  }

  /**
   * Authorizes the credentials of the login credentials username and password
   *
   * @param username the username input
   * @param password the password input
   */
  public void loginAuthorization(String username, String password) {
    if (loginView != null) {
      loginView.showProgress();
    }

    loginInteractor.login(username, password, this);
  }

  public void onDestroy() {
    loginView = null;
  }

  /**
   * Sets error message given an error with the username
   *
   * @param message the message for the error
   */
  @Override
  public void onUsernameError(String message) {
    if (loginView != null) {
      loginView.setUsernameError(message);
      loginView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  @Override
  public void onPasswordError(String message) {
    if (loginView != null) {
      loginView.setPasswordError(message);
      loginView.hideProgress();
    }
  }

  /** Handles the successful validation of login information */
  @Override
  public void onSuccess(UserInformation userInformation) {
    if (loginView != null) {
      loginView.switchToHomeScreen(userInformation);
    }
  }

  /** Handles the failure during validation of login information */
  @Override
  public void onFail() {
    if (loginView != null) {
      loginView.sendInvalidAuthorizationMessage();
    }
  }
}
