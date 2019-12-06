package com.example.paymentmodernization.CreateInvoice;

/**
 * CreateInvoicePresenter retrieves data from the CreateInvoiceInteractor and formats it for the
 * CreateInvoiceView. Implements OnCreateFinishedListener to handle invoice creation events *
 */
public class CreateInvoicePresenter implements CreateInvoiceInteractor.OnCreateFinishedListener {
  /** The CreateInvoiceView that this CreateInformationPresenter formats information for */
  private CreateInvoiceView createInvoiceView;
  /** The CreateInvoiceInteractor that this CreateInvoicePresenter retrieves data from */
  private CreateInvoiceInteractor createInvoiceInteractor;

  /**
   * Constructs a new CreateInvoicePresenter with given createInvoiceView and
   * createInvoiceInteractor
   *
   * @param createInvoiceView the CreateInvoiceView corresponding to this CreateInvoicePresenter
   * @param createInvoiceInteractor the CreateInvoiceInteractor corresponding to this
   *     CreateInvoicePresenter
   */
  CreateInvoicePresenter(
      CreateInvoiceView createInvoiceView, CreateInvoiceInteractor createInvoiceInteractor) {
    this.createInvoiceView = createInvoiceView;
    this.createInvoiceInteractor = createInvoiceInteractor;
  }

  /**
   * Attempts to create an invoice with the given information
   *
   * @param authToken the authorization token of the user
   * @param business the business receiving the invoice
   * @param deliveryPerson the delivery person associated with the invoice
   * @param invoiceDate the date that the invoice was created
   * @param dueDate the due date of te invoice
   * @param items the items associated with the invoice
   */
  public void createInvoice(
      String authToken,
      String business,
      String deliveryPerson,
      String invoiceDate,
      String dueDate,
      String items) {
    if (createInvoiceView != null) {
      createInvoiceView.showProgress();
    }
    createInvoiceInteractor.createInvoice(
        authToken, business, deliveryPerson, invoiceDate, dueDate, items, this);
  }

  public void onDestroy() {
    createInvoiceView = null;
  }

  /**
   * Sets error message given an error with the business
   *
   * @param message the message for the error
   */
  @Override
  public void onBusinessError(String message) {
    if (createInvoiceView != null) {
      createInvoiceView.setBusinessError(message);
      createInvoiceView.hideProgress();
    }
  }

  /**
   * Sets error message given an error with the due date
   *
   * @param message the message for the error
   */
  @Override
  public void onDueDateError(String message) {
    if (createInvoiceView != null) {
      createInvoiceView.setDueDateError(message);
      createInvoiceView.hideProgress();
    }
  }

  /** Handles errors within creation of invoice items */
  @Override
  public void onItemsError() {
    if (createInvoiceView != null) {
      createInvoiceView.sendInvalidItemsMessage();
      createInvoiceView.hideProgress();
    }
  }

  /** Handles the successful creation of new invoice */
  @Override
  public void onSuccess() {
    if (createInvoiceView != null) {
      createInvoiceView.switchToHome();
    }
  }
  /** Handles failure of invoice creation */
  @Override
  public void onFail() {
    if (createInvoiceView != null) {
      createInvoiceView.sendInvalidInvoiceMessage();
    }
  }
}
