package com.example.paymentmodernization.Login;
/** An object to store login authorization information */
public class LoginAuthorization {

  /** Whether or not the login information is valid */
  private String isValid;
  /** The authentication token of the corresponding user */
  private String authToken;

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
