package com.example.paymentmodernization.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

public class InvoiceItem implements Parcelable {

  public static final Parcelable.Creator<InvoiceItem> CREATOR =
      new Parcelable.Creator<InvoiceItem>() {
        public InvoiceItem createFromParcel(Parcel parcel) {
          return new InvoiceItem(parcel);
        }

        public InvoiceItem[] newArray(int size) {
          return new InvoiceItem[size];
        }
      };
  private String quantity;
  private String price;
  private String description;

  private InvoiceItem(Parcel parcel) {
    quantity = parcel.readString();
    price = parcel.readString();
    description = parcel.readString();
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(quantity);
    parcel.writeString(price);
    parcel.writeString(description);
  }
}
