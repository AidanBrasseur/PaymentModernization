package com.example.paymentmodernization.HomePage;

import com.example.paymentmodernization.ui.home.Invoice;
import com.example.paymentmodernization.ui.home.InvoiceItem;

import java.util.ArrayList;

public class InvoiceCard {
  private String supplierToCompany;
  private String delivery;
  private String status;
  private String invoiceId;
  private ArrayList<InvoiceItem> items;
  private Invoice invoice;

  public InvoiceCard(Invoice invoice, String supplierToCompany, String delivery, String status, ArrayList<InvoiceItem> items, String invoiceId) {
    this.supplierToCompany = supplierToCompany;
    this.delivery = delivery;
    this.status = status;
    this.invoiceId = invoiceId;
    this.items = items;
    this.invoice = invoice;
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

  public String getInvoiceId() {
    return invoiceId;
  }

  public ArrayList<InvoiceItem> getItems(){
    return items;
  }

  public Invoice getInvoice() {
    return invoice;
  }
}
