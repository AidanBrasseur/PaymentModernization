package com.example.paymentmodernization.SignUp;

/**
 * DriverSignUpPresenter retrieves data from the DriverSignUpInteractor and formats it for the
 * DriverSignUpView. Implements OnSignUpFinishedListener to handle sign-up events *
 */
public class DriverSignUpPresenter implements DriverSignUpInteractor.OnSignUpFinishedListener {

  /** The DriverSignUpView that this DriverSignUpPresenter formats information for */
  private DriverSignUpView driverSignUpView;
  /** The DriverSignUpInteractor that this DriverSignUpPresenter retrieves data from */
  private DriverSignUpInteractor driverSignUpInteractor;

  /**
   * Constructs a new DriverSignUpPresenter with given driverSignUpView and driverSignUpInteractor
   *
   * @param driverSignUpView the DriverSignUpView corresponding to this DriverSignUpPresenter
   * @param driverSignUpInteractor the DriverSignUpInteractor corresponding to this
   *     DriverSignUpPresenter
   */
  DriverSignUpPresenter(
      DriverSignUpView driverSignUpView, DriverSignUpInteractor driverSignUpInteractor) {
    this.driverSignUpView = driverSignUpView;
    this.driverSignUpInteractor = driverSignUpInteractor;
  }
  /**
   * Signs up new driver with given username, password and fullName
   *
   * @param username the username input
   * @param password the password input
   * @param fullName the full name of the new user
   */
  public void signUpUser(
      String username,
      String password,
      String fullName,
      String userType,
      String accountNum,
      String bank,
      String streetAddress,
      String city,
      String region,
      String country,
      String postalCode) {
    if (driverSignUpView != null) {
      driverSignUpView.showProgress();
    }
    driverSignUpInteractor.signUpUser(
        username,
        password,
        fullName,
        userType,
        accountNum,
        bank,
        streetAddress,
        city,
        region,
        country,
        postalCode,
        this);
  }

  /**
   * Signs up new driver with given username, password and fullName
   *
   * @param username the username input
   * @param password the password input
   * @param fullName the full name of the new user
   */
  public void signUpUser(String username, String password, String fullName) {
    if (driverSignUpView != null) {
      driverSignUpView.showProgress();
    }
    driverSignUpInteractor.signUpUser(username, password, fullName, this);
  }

  public void onDestroy() {
    driverSignUpView = null;
  }

  /**
   * Sets error message given an error with the username
   *
   * @param message the message for the error
   */
  @Override
  public void onUsernameError(String message) {
    if (driverSignUpView != null) {
      driverSignUpView.setUsernameError(message);
      driverSignUpView.hideProgress();
    }
  }

  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  @Override
  public void onPasswordError(String message) {
    if (driverSignUpView != null) {
      driverSignUpView.setPasswordError(message);
      driverSignUpView.hideProgress();
    }
  }
  /** Handles the successful sign-up new driver */
  @Override
  public void onSuccess() {
    if (driverSignUpView != null) {
      driverSignUpView.switchToLogin();
    }
  }
  /** Handles failure during sign-up of new driver */
  @Override
  public void onFail() {
    if (driverSignUpView != null) {
      driverSignUpView.sendInvalidSignUpMessage();
    }
  }
}
