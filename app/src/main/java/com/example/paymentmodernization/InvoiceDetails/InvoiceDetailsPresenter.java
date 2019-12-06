package com.example.paymentmodernization.InvoiceDetails;

import com.example.paymentmodernization.InvoicesHomePage.Invoice;
/**
 * InvoiceDetailsPresenter retrieves data from the InvoiceDetailsInteractor and formats it for the
 * InvoiceDetailsView. Implements OnStatusUpdatedListener to handle status update events *
 */
public class InvoiceDetailsPresenter implements InvoiceDetailsInteractor.OnStatusUpdatedListener {
  /** The InvoiceDetailsView that this InvoiceDetailsPresenter formats information for */
  private InvoiceDetailsView invoiceDetailsView;
  /** The InvoiceDetailsInteractor that this InvoiceDetailsPresenter retrieves data from */
  private InvoiceDetailsInteractor invoiceDetailsInteractor;

  /**
   * Constructs a new InvoiceDetailsPresenter with given invoiceDetailsView and
   * invoiceDetailsInteractor
   *
   * @param invoiceDetailsView the InvoiceDetailsView corresponding to this InvoiceDetailPresenter
   * @param invoiceDetailsInteractor the InvoiceDetailsInteracotr corresponding to this
   *     InvoiceDetailsPresenter
   */
  InvoiceDetailsPresenter(
      InvoiceDetailsView invoiceDetailsView, InvoiceDetailsInteractor invoiceDetailsInteractor) {
    this.invoiceDetailsInteractor = invoiceDetailsInteractor;
    this.invoiceDetailsView = invoiceDetailsView;
  }

  /**
   * Updates the status of the invoice with invoiceId to newStatus
   *
   * @param invoiceId the id of the invoice to be updated
   * @param authToken the authorization token of the user
   * @param newStatus the new status
   */
  public void updateStatus(String invoiceId, String authToken, String newStatus) {
    invoiceDetailsInteractor.updateStatus(invoiceId, authToken, newStatus, this);
  }

  /**
   * Updates the payment date if userType is a small business and updates delivery date if userType
   * is a delivery person
   *
   * @param invoiceId the id of the invoice to be updated
   * @param authToken the authorization token of the user
   * @param newDate the new date
   * @param userType the type of the user
   */
  public void updateDate(String invoiceId, String authToken, String newDate, String userType) {
    invoiceDetailsInteractor.updateDate(invoiceId, authToken, newDate, userType, this);
  }

  /**
   * Updates the driver assigned to the invoice
   *
   * @param invoiceId the id of the invoice to be updated
   * @param authToken the authorization token of the user
   * @param newDriver the new driver
   */
  public void updateDriver(String invoiceId, String authToken, String newDriver) {
    invoiceDetailsInteractor.updateDriver(invoiceId, authToken, newDriver, this);
  }

  /**
   * Gets all details about the invoice
   *
   * @param invoiceId the id of the invoice that data is being retrieved for
   * @param authToken the authorization token of the user
   */
  public void getInvoiceDetails(String invoiceId, String authToken) {
    invoiceDetailsInteractor.getInvoiceDetails(invoiceId, authToken, this);
  }
  /** Handles the successful update of status to newStatus */
  @Override
  public void onStatusUpdateSuccess(String newStatus) {
    invoiceDetailsView.statusUpdatedSuccess(newStatus);
  }
  /** Handles the failure of update of status to newStatus */
  @Override
  public void onStatusUpdateFailure(String newStatus) {}
  /** Handles successful update of driver */
  @Override
  public void onDriverUpdateSuccess() {
    invoiceDetailsView.driverUpdatedSuccess();
  }
  /** Handles failure of update of driver */
  @Override
  public void onDriverUpdateFailure() {}
  /** Handles successful update of date */
  @Override
  public void onDateUpdateSuccess() {
    invoiceDetailsView.dateUpdatedSuccess();
  }
  /** Handles failure of update of date */
  @Override
  public void onDateUpdateFailure() {}
  /** Handles successful retrieval of invoice data */
  @Override
  public void onSuccessDetails(Invoice invoice) {
    invoiceDetailsView.setTextFields(invoice);
  }
}
