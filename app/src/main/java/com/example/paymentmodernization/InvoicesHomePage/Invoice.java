package com.example.paymentmodernization.InvoicesHomePage;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
/** An invoice placed by a business. Implements Parcelable */
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

  /**
   * Constructor for invoice from given parcel
   *
   * @param parcel the parcel that the invoice object is being created from
   */
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
  /**
   * Get delivery person
   *
   * @return Delivery person
   */
  public String getDeliveryPerson() {
    return driver;
  }
  /**
   * Set delivery person
   *
   * @param deliveryPerson New delivery person
   */
  public void setDeliveryPerson(String deliveryPerson) {
    this.driver = driver;
  }
  /**
   * Get supplier address
   *
   * @return supplier address
   */
  public Address getSupplierAddress() {
    return supplierAddress;
  }
  /**
   * Set supplier address
   *
   * @param supplierAddress New supplier address
   */
  public void setSupplierAddress(Address supplierAddress) {
    this.supplierAddress = supplierAddress;
  }

  public Address getBusinessAddress() {
    return businessAddress;
  }

  public void setBusinessAddress(Address businessAddress) {
    this.businessAddress = businessAddress;
  }

  /**
   * Get business
   *
   * @return Business
   */
  public String getBusiness() {
    return business;
  }
  /**
   * Set business
   *
   * @param business New business
   */
  public void setBusiness(String business) {
    this.business = business;
  }
  /**
   * Get supplier
   *
   * @return Supplier
   */
  public String getSupplier() {
    return supplier;
  }
  /**
   * Set supplier
   *
   * @param supplier New supplier
   */
  public void setSupplier(String supplier) {
    this.supplier = supplier;
  }
  /**
   * Get due date
   *
   * @return Due date
   */
  public String getDueDate() {
    return dueDate;
  }
  /**
   * Set due date
   *
   * @param dueDate New due date
   */
  public void setDueDate(String dueDate) {
    this.dueDate = dueDate;
  }
  /**
   * Get invoice id
   *
   * @return invoice id
   */
  public String getInvoiceId() {
    return invoiceId;
  }
  /**
   * Set invoice id
   *
   * @param invoiceId New invoiceId
   */
  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }
  /**
   * Get invoice date
   *
   * @return Invoice date
   */
  public String getInvoiceDate() {
    return invoiceDate;
  }
  /**
   * Set invoice date
   *
   * @param invoiceDate New invoice date
   */
  public void setInvoiceDate(String invoiceDate) {
    this.invoiceDate = invoiceDate;
  }
  /**
   * Get delivered date
   *
   * @return Delivered date
   */
  public String getDeliveryDate() {
    return deliveryDate;
  }
  /**
   * Set delivered date
   *
   * @param deliveryDate New delivered date
   */
  public void setDeliveryDate(String deliveryDate) {
    this.deliveryDate = deliveryDate;
  }
  /**
   * Get payment date
   *
   * @return Payment date
   */
  public String getPaymentDate() {
    return paymentDate;
  }
  /**
   * Set payment date
   *
   * @param paymentDate New payment date
   */
  public void setPaymentDate(String paymentDate) {
    this.paymentDate = paymentDate;
  }
  /**
   * Get invoice items
   *
   * @return Invoice items
   */
  public ArrayList<InvoiceItem> getItems() {
    return items;
  }
  /**
   * Set invoice items
   *
   * @param items New invoice items
   */
  public void setItems(ArrayList<InvoiceItem> items) {
    this.items = items;
  }
  /**
   * Get status
   *
   * @return Status
   */
  public String getStatus() {
    if (status == null) {
      return "";
    }
    return status;
  }
  /**
   * Set status
   *
   * @param status New status
   */
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
