package com.example.paymentmodernization.ui.home;

import com.example.paymentmodernization.ui.home.Invoice;

import java.util.ArrayList;

/** An object to store invoices of the user information */
public class Invoices {

  /** The invoices for this user */
  private ArrayList<Invoice> invoices;

  /**
   * get the invoices
   *
   * @return invoices
   */
  public ArrayList<Invoice> getInvoices() {
    return invoices;
  }

  /**
   * set invoices
   *
   * @param invoices
   */
  public void setInvoices(ArrayList<Invoice> invoices) {
    this.invoices = invoices;
  }
}
