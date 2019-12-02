package com.example.paymentmodernization.InvoiceDetails;

import com.example.paymentmodernization.ui.home.Invoice;

public interface InvoiceDetailsView {

  void statusUpdatedSuccess(String newStatus);
  void setTextFields(Invoice invoice);

  //void getInvoiceDetails(Invoice invoice, String authToken);
}
