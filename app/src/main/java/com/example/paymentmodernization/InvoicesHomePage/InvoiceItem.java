package com.example.paymentmodernization.InvoicesHomePage;

import android.os.Parcel;
import android.os.Parcelable;

/** An invoice item. Implements Parcelable */
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

  /**
   * Constructs new InvoiceItem from given parcel
   *
   * @param parcel the parcel that the invoice item object is being created from
   */
  private InvoiceItem(Parcel parcel) {
    quantity = parcel.readString();
    price = parcel.readString();
    description = parcel.readString();
  }

  /**
   * Gets quantity of invoice item
   *
   * @return quantity of invoice item
   */
  public String getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of invoice item
   *
   * @param quantity the new quantity of invoice item
   */
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
  /**
   * Gets price of invoice item
   *
   * @return price of invoice item
   */
  public String getPrice() {
    return price;
  }
  /**
   * Sets the price of invoice item
   *
   * @param price the new price of invoice item
   */
  public void setPrice(String price) {
    this.price = price;
  }
  /**
   * Gets description of invoice item
   *
   * @return description of invoice item
   */
  public String getDescription() {
    return description;
  }
  /**
   * Sets the description of invoice item
   *
   * @param description the new description of invoice item
   */
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
