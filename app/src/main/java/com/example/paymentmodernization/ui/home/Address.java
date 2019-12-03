package com.example.paymentmodernization.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    private String region;
    private String country;
    private String postalCode;

    private Address(Parcel parcel) {
        streetAddress = parcel.readString();
        city = parcel.readString();
        region = parcel.readString();
        country = parcel.readString();
        postalCode = parcel.readString();
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
