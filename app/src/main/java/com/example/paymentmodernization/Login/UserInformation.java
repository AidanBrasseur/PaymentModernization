package com.example.paymentmodernization.Login;

import android.os.Parcel;
import android.os.Parcelable;

/** An object to store user information */
public class UserInformation implements Parcelable {

  public static final Parcelable.Creator<UserInformation> CREATOR =
      new Parcelable.Creator<UserInformation>() {
        public UserInformation createFromParcel(Parcel parcel) {
          return new UserInformation(parcel);
        }

        public UserInformation[] newArray(int size) {
          return new UserInformation[size];
        }
      };

  private String isValid;
  private String authToken;
  private String fullName;
  private String userType;
  private String username;
  private String bankNum;
  private String bank;
  private String country;
  private String streetAddress;
  private String city;
  private String postalCode;
  private String region;

  /**
   * Constructor for user information from given parcel
   *
   * @param parcel the parcel that the user information object is being created from
   */
  private UserInformation(Parcel parcel) {
    isValid = parcel.readString();
    authToken = parcel.readString();
    fullName = parcel.readString();
    userType = parcel.readString();
    username = parcel.readString();
    bankNum = parcel.readString();
    bank = parcel.readString();
    country = parcel.readString();
    streetAddress = parcel.readString();
    city = parcel.readString();
    postalCode = parcel.readString();
    region = parcel.readString();
  }
  /**
   * Get username
   *
   * @return username of user
   */
  public String getUsername() {
    return username;
  }
  /**
   * Set username
   *
   * @param username New username
   */
  public void setUsername(String username) {
    this.username = username;
  }
  /**
   * Get bank account number
   *
   * @return bank account number of user
   */
  public String getBankNum() {
    return bankNum;
  }
  /**
   * Set bank account number
   *
   * @param bankNum New bank number
   */
  public void setBankNum(String bankNum) {
    this.bankNum = bankNum;
  }
  /**
   * Get bank
   *
   * @return bank of user
   */
  public String getBank() {
    return bank;
  }
  /**
   * Set bank
   *
   * @param bank New bank
   */
  public void setBank(String bank) {
    this.bank = bank;
  }
  /**
   * Get country
   *
   * @return country of user
   */
  public String getCountry() {
    return country;
  }
  /**
   * Set country
   *
   * @param country New country
   */
  public void setCountry(String country) {
    this.country = country;
  }
  /**
   * Get street address
   *
   * @return street address of user
   */
  public String getStreetAddress() {
    return streetAddress;
  }
  /**
   * Set street address
   *
   * @param streetAddress New street address
   */
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }
  /**
   * Get city
   *
   * @return city of user
   */
  public String getCity() {
    return city;
  }
  /**
   * Set city
   *
   * @param city New city
   */
  public void setCity(String city) {
    this.city = city;
  }
  /**
   * Get postal code
   *
   * @return postal code of user
   */
  public String getPostalCode() {
    return postalCode;
  }
  /**
   * Set postal code
   *
   * @param postalCode New postal code
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  /**
   * Get region
   *
   * @return region of user
   */
  public String getRegion() {
    return region;
  }
  /**
   * Set region
   *
   * @param region New region
   */
  public void setRegion(String region) {
    this.region = region;
  }
  /**
   * Get user type
   *
   * @return type of user
   */
  public String getUserType() {
    return userType;
  }
  /**
   * Set user type
   *
   * @param userType New user type
   */
  public void setUserType(String userType) {
    this.userType = userType;
  }
  /**
   * Get whether the user is valid
   *
   * @return validity of user
   */
  public String getIsValid() {
    return isValid;
  }
  /**
   * Set validity of user
   *
   * @param isValid New validity
   */
  public void setIsValid(String isValid) {
    this.isValid = isValid;
  }
  /**
   * Get authorization token
   *
   * @return authorization token of user
   */
  public String getAuthToken() {
    return authToken;
  }
  /**
   * Set authorization token
   *
   * @param authToken New authorization token
   */
  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }
  /**
   * Get full name
   *
   * @return full name of user
   */
  public String getFullName() {
    return fullName;
  }
  /**
   * Set full name
   *
   * @param fullName New full name
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(isValid);
    parcel.writeString(authToken);
    parcel.writeString(fullName);
    parcel.writeString(userType);
    parcel.writeString(username);
    parcel.writeString(bankNum);
    parcel.writeString(bank);
    parcel.writeString(country);
    parcel.writeString(streetAddress);
    parcel.writeString(city);
    parcel.writeString(postalCode);
    parcel.writeString(region);
  }
}
