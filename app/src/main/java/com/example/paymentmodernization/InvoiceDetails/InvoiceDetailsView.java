package com.example.paymentmodernization.InvoiceDetails;

import com.example.paymentmodernization.InvoicesHomePage.Invoice;
/** An interface for the methods necessary for displaying and updating specific invoices */
public interface InvoiceDetailsView {
  /** Handles the successful update of status to newStatus */
  void statusUpdatedSuccess(String newStatus);

  /** Fills the text fields on the page with the invoice data */
  void setTextFields(Invoice invoice);

  /** Handles successful update of date */
  void dateUpdatedSuccess();

  /** Handles successful update of driver */
  void driverUpdatedSuccess();
}
