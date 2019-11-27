package com.example.paymentmodernization.HomePage;

public class InvoiceCard {
  private String supplierToCompany;
  private String delivery;
  private String status;

  public InvoiceCard(String supplierToCompany, String delivery, String status) {
    this.supplierToCompany = supplierToCompany;
    this.delivery = delivery;
    this.status = status;
  }

  public String getDelivery() {
    return delivery;
  }

  public String getSupplierToCompany() {
    return supplierToCompany;
  }

  public String getStatus() {
    return status;
  }
}
