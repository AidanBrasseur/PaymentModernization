package com.example.paymentmodernization.HomePage;

import java.io.Serializable;
import java.util.ArrayList;

public class Invoice implements Serializable {
  private String business;
  private String supplier;
  private String dueDate;
  private String invoiceId;
  private String invoiceDate;
  private String deliveryDate;
  private String paymentDate;
  private ArrayList<InvoiceItem> items;
  private String status;

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
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
