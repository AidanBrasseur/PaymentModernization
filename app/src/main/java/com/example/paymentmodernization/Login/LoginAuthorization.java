package com.example.paymentmodernization.Login;
/** An object to store login authorization information */
public class LoginAuthorization {

  /** Whether or not the login information is valid */
  private String isValid;
  /** The authentication token of the corresponding user */
  private String authToken;
  /** The type of the user */
  private String userType;

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getIsValid() {
    return isValid;
  }

  public void setIsValid(String isValid) {
    this.isValid = isValid;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }
}
