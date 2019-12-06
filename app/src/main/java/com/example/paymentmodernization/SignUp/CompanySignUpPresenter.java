package com.example.paymentmodernization.SignUp;

/**
 * CompanySignUpPresenter retrieves data from the CompanySignUpInteractor and formats it for the
 * CompanySignUpView. Implements OnCompanySignUpFinishedListener to handle company sign-up events *
 */
public class CompanySignUpPresenter
    implements CompanySignUpInteractor.OnCompanySignUpFinishedListener {

  /** The CompanySignUpView that this CompanySignUpPresenter formats information for */
  private CompanySignUpView companySignUpView;
  /** The CompanySignUpInteractor that this CompanySignUpPresenter retrieves data from */
  private CompanySignUpInteractor companySignUpInteractor;

  /**
   * Constructs a new CompanySignUpPresenter with given companySignUpView and
   * companySignUpInteractor
   *
   * @param companySignUpView the CompanySignUpView corresponding to this CompanySignUpPresenter
   * @param companySignUpInteractor the CompanySignUpInteractor corresponding to this
   *     CompanySignUpPresenter
   */
  CompanySignUpPresenter(
      CompanySignUpView companySignUpView, CompanySignUpInteractor companySignUpInteractor) {
    this.companySignUpView = companySignUpView;
    this.companySignUpInteractor = companySignUpInteractor;
  }
  // TODO: Documentation. Probably going to move all this to new classes honestly
  /**
   * Signs up new user with given username, password and fullName
   *
   * @param username the username input
   * @param password the password input
   * @param fullName the full name of the new user
   */
  void signUpUser(
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
    if (companySignUpView != null) {
      companySignUpView.showProgress();
    }
    companySignUpInteractor.signUpUser(
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

  void onDestroy() {
    companySignUpView = null;
  }

  /**
   * Sets error message given an error with the username
   *
   * @param message the message for the error
   */
  @Override
  public void onUsernameError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setUsernameError(message);
      companySignUpView.hideProgress();
    }
  }

  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  @Override
  public void onPasswordError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setPasswordError(message);
      companySignUpView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the full name
   *
   * @param message the message for the error
   */
  @Override
  public void onFullNameError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setFullNameError(message);
      companySignUpView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the bank account number
   *
   * @param message the message for the error
   */
  @Override
  public void onBankNumError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setBankNumError(message);
      companySignUpView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the street address
   *
   * @param message the message for the error
   */
  @Override
  public void onStreetAddressError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setStreetAddressError(message);
      companySignUpView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the city
   *
   * @param message the message for the error
   */
  @Override
  public void onCityError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setCityError(message);
      companySignUpView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the country
   *
   * @param message the message for the error
   */
  @Override
  public void onCountryError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setCountryError(message);
      companySignUpView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the region
   *
   * @param message the message for the error
   */
  @Override
  public void onRegionError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setRegionError(message);
      companySignUpView.hideProgress();
    }
  }
  /**
   * Sets error message given an error with the postal code
   *
   * @param message the message for the error
   */
  @Override
  public void onPostalCodeError(String message) {
    if (companySignUpView != null) {
      companySignUpView.setPostalCodeError(message);
      companySignUpView.hideProgress();
    }
  }
  /** Handles the successful sign-up new company */
  @Override
  public void onSuccess() {
    if (companySignUpView != null) {
      companySignUpView.switchToLogin();
    }
  }
  /** Handles failure during sign-up of new company */
  @Override
  public void onFail() {
    if (companySignUpView != null) {
      companySignUpView.sendInvalidSignUpMessage();
    }
  }
}
