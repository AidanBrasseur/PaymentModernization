package com.example.paymentmodernization.ui.home;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Invoice implements Parcelable {
  public static final Parcelable.Creator<Invoice> CREATOR =
      new Parcelable.Creator<Invoice>() {
        public Invoice createFromParcel(Parcel parcel) {
          return new Invoice(parcel);
        }

        public Invoice[] newArray(int size) {
          return new Invoice[size];
        }
      };
  private Address supplierAddress;
  private String business;
  private String driver;
  private String supplier;
  private String dueDate;
  private String invoiceId;
  private String invoiceDate;
  private String deliveryDate;
  private String paymentDate;
  private Address businessAddress;
  private ArrayList<InvoiceItem> items;
  private String status;
  private Invoice(Parcel parcel) {
    supplierAddress = parcel.readParcelable(Address.class.getClassLoader());
    business = parcel.readString();
    driver = parcel.readString();
    supplier = parcel.readString();
    dueDate = parcel.readString();
    invoiceId = parcel.readString();
    invoiceDate = parcel.readString();
    deliveryDate = parcel.readString();
    paymentDate = parcel.readString();
    businessAddress = parcel.readParcelable(Address.class.getClassLoader());
    items = parcel.readArrayList(InvoiceItem.class.getClassLoader());
    status = parcel.readString();
  }

  public static Creator<Invoice> getCREATOR() {
    return CREATOR;
  }

  public String getDriver() {
    return driver;
  }

  public void setDriver(String driver) {
    this.driver = driver;
  }

  public Address getSupplierAddress() {
    return supplierAddress;
  }

  public void setSupplierAddress(Address supplierAddress) {
    this.supplierAddress = supplierAddress;
  }

  public Address getBusinessAddress() {
    return businessAddress;
  }

  public void setBusinessAddress(Address businessAddress) {
    this.businessAddress = businessAddress;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getSupplier() {
    return supplier;
  }

  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }

  public String getDueDate() {
    return dueDate;
  }

  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getInvoiceDate() {
    return invoiceDate;
  }

  public void setInvoiceDate(String invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  public String getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public String getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(String paymentDate) {
    this.paymentDate = paymentDate;
  }

  public ArrayList<InvoiceItem> getItems() {
    return items;
  }

  public void setItems(ArrayList<InvoiceItem> items) {
    this.items = items;
  }

  public String getStatus() {
    if (status == null) {
      return "";
    }
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeParcelable(supplierAddress, i);
    parcel.writeString(business);
    parcel.writeString(driver);
    parcel.writeString(supplier);
    parcel.writeString(dueDate);
    parcel.writeString(invoiceId);
    parcel.writeString(invoiceDate);
    parcel.writeString(deliveryDate);
    parcel.writeString(paymentDate);
    parcel.writeParcelable(businessAddress, i);
    parcel.writeList(items);
    parcel.writeString(status);
  }
}
