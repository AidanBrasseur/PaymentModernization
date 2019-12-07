package com.example.paymentmodernization.InvoicesHomePage;

import java.util.ArrayList;

/**
 * a single invoice card to display in recycler view.
 */

class InvoiceCard {
  private String supplierToCompany;
  private String delivery;
  private String status;
  private String invoiceId;
  private ArrayList<InvoiceItem> items;
  private Invoice invoice;

  InvoiceCard(
      Invoice invoice,
      String supplierToCompany,
      String delivery,
      String status,
      ArrayList<InvoiceItem> items,
      String invoiceId) {
    this.supplierToCompany = supplierToCompany;
    this.delivery = delivery;
    this.status = status;
    this.invoiceId = invoiceId;
    this.items = items;
    this.invoice = invoice;
  }

  String getDelivery() {
    return delivery;
  }

  String getSupplierToCompany() {
    return supplierToCompany;
  }

  String getStatus() {
    return status;
  }

  String getInvoiceId() {
    return invoiceId;
  }

  ArrayList<InvoiceItem> getItems() {
    return items;
  }

  Invoice getInvoice() {
    return invoice;
  }
}
