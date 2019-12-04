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

  public void updateDate(String invoiceId, String authToken, String newDate, String userType) {
    invoiceDetailsInteractor.updateDate(invoiceId, authToken, newDate, userType, this);
  }

  public void updateDriver(String invoiceId, String authToken, String newDriver) {
    invoiceDetailsInteractor.updateDriver(invoiceId, authToken, newDriver, this);
  }

  public void getInvoiceDetails(String invoiceId, String authToken) {
    invoiceDetailsInteractor.getInvoiceDetails(invoiceId, authToken, this);
  }

  @Override
  public void onStatusUpdateSuccess(String newStatus) {
    invoiceDetailsView.statusUpdatedSuccess(newStatus);
  }

  @Override
  public void onStatusUpdateFailure(String newStatus) {}

  @Override
  public void onDriverUpdateSuccess(String newDriver) {
    invoiceDetailsView.driverUpdatedSuccess(newDriver);
  }

  @Override
  public void onDriverUpdateFailure(String newDriver) {}

  @Override
  public void onDateUpdateSuccess(String newDate) {
    invoiceDetailsView.dateUpdatedSuccess(newDate);
  }

  @Override
  public void onDateUpdateFailure(String newDate) {}

  @Override
  public void onSuccessDetails(Invoice invoice) {
    invoiceDetailsView.setTextFields(invoice);
  }
}
