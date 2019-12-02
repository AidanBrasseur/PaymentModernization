package com.example.paymentmodernization.InvoiceDetails;

import com.example.paymentmodernization.ui.home.Invoice;

public class InvoiceDetailsPresenter implements InvoiceDetailsInteractor.OnStatusUpdatedListener {
  private InvoiceDetailsView invoiceDetailsView;
  private InvoiceDetailsInteractor invoiceDetailsInteractor;

  InvoiceDetailsPresenter(
      InvoiceDetailsView invoiceDetailsView, InvoiceDetailsInteractor invoiceDetailsInteractor) {
    this.invoiceDetailsInteractor = invoiceDetailsInteractor;
    this.invoiceDetailsView = invoiceDetailsView;
  }

  public void updateStatus(String invoiceId, String authToken, String newStatus) {
    invoiceDetailsInteractor.updateStatus(invoiceId, authToken, newStatus, this);
  }

  @Override
  public void onSuccess(String newStatus) {
    invoiceDetailsView.statusUpdatedSuccess(newStatus);
  }

  public void getInvoiceDetails(String invoiceId, String authToken){
    invoiceDetailsInteractor.getInvoiceDetails(invoiceId, authToken, this);
  }



  @Override
  public void onFailure(String newStatus) {
    // TODO handleFailure
  }

  @Override
  public void onSuccessDetails(Invoice invoice) {
    invoiceDetailsView.setTextFields(invoice);
  }
}
