package com.example.paymentmodernization.SignUp;

/**
 * SignUpPresenter retrieves data from the SignUpInteractor and formats it for the SignUpView.
 * Implements OnSignUpFinishedListener to handle sign-up events *
 */
public class SignUpPresenter implements SignUpInteractor.OnSignUpFinishedListener {

  /** The SignUpView that this SignUpPresenter formats information for */
  private SignUpView signUpView;
  /** The SignUpInteractor that this SignUpPresenter retrieves data from */
  private SignUpInteractor signUpInteractor;

  /**
   * Constructs a new SignUpPresenter with given signUpView and signUpInteractor
   *
   * @param signUpView the SignUpView corresponding to this SignUpPresenter
   * @param signUpInteractor the SignUpInteractor corresponding to this SignUpPresenter
   */
  SignUpPresenter(SignUpView signUpView, SignUpInteractor signUpInteractor) {
    this.signUpView = signUpView;
    this.signUpInteractor = signUpInteractor;
  }

  /**
   * Signs up new user with given username, password and fullName
   *
   * @param username the username input
   * @param password the password input
   * @param fullName the full name of the new user
   */
  public void signUpUser(String username, String password, String fullName) {
    if (signUpView != null) {
      signUpView.showProgress();
    }
    signUpInteractor.signUpUser(username, password, fullName, this);
  }

  public void onDestroy() {
    signUpView = null;
  }

  /**
   * Sets error message given an error with the username
   *
   * @param message the message for the error
   */
  @Override
  public void onUsernameError(String message) {
    if (signUpView != null) {
      signUpView.setUsernameError(message);
      signUpView.hideProgress();
    }
  }

  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  @Override
  public void onPasswordError(String message) {
    if (signUpView != null) {
      signUpView.setPasswordError(message);
      signUpView.hideProgress();
    }
  }
  /** Handles the successful sign-up new user */
  @Override
  public void onSuccess() {
    if (signUpView != null) {
      signUpView.switchToLogin();
    }
  }
  /** Handles failure during sign-up of new user */
  @Override
  public void onFail() {
    if (signUpView != null) {
      signUpView.sendInvalidSignUpMessage();
    }
  }
}
