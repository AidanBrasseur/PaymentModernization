package com.example.paymentmodernization.Login;

import android.os.Parcel;
import android.os.Parcelable;

/** An object to store login authorization information */
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
  /** Whether or not the login information is valid */
  private String isValid;
  /** The authentication token of the corresponding user */
  private String authToken;
  /** The full name of the user */
  private String fullName;
  /** The type of the user */
  private String userType;

  private String username;
  private String bankNum;
  private String bank;
  private String country;
  private String streetAddress;
  private String city;
  private String postalCode;
  private String region;

  // example constructor that takes a Parcel and gives you an object populated with it's values
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getBankNum() {
    return bankNum;
  }

  public void setBankNum(String bankNum) {
    this.bankNum = bankNum;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

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

  public String getFullName() {
    return fullName;
  }

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
