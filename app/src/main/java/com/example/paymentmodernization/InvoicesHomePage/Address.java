package com.example.paymentmodernization.InvoicesHomePage;

import android.os.Parcel;
import android.os.Parcelable;

/** An parcelable address */
public class Address implements Parcelable {

  public static final Parcelable.Creator<Address> CREATOR =
      new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel parcel) {
          return new Address(parcel);
        }

        public Address[] newArray(int size) {
          return new Address[size];
        }
      };
  private String streetAddress;
  private String city;
  private String region;
  private String country;
  private String postalCode;

  /**
   * Constructor for Address from given parcel
   *
   * @param parcel the parcel that the address object is being created from
   */
  private Address(Parcel parcel) {
    streetAddress = parcel.readString();
    city = parcel.readString();
    region = parcel.readString();
    country = parcel.readString();
    postalCode = parcel.readString();
  }
  /**
   * Get street address
   *
   * @return Street address
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
   * @return City
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
   * Get region
   *
   * @return Region
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
   * Get country
   *
   * @return Country
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
   * Get postal code
   *
   * @return Postal code
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

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(streetAddress);
    parcel.writeString(city);
    parcel.writeString(region);
    parcel.writeString(country);
    parcel.writeString(postalCode);
  }
}
