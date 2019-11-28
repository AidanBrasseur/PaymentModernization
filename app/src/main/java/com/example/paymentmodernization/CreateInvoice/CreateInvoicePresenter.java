package com.example.paymentmodernization.CreateInvoice;

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
  // TODO: Documentation. Probably going to move all this to new classes honestly

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
   * Sets error message given an error with the username
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

  @Override
  public void onDueDateError(String message) {
    if (createInvoiceView != null) {
      createInvoiceView.setDueDateError(message);
      createInvoiceView.hideProgress();
    }
  }

  @Override
  public void onItemsError(String message) {
    if (createInvoiceView != null) {
      createInvoiceView.setItemsError(message);
      createInvoiceView.hideProgress();
    }
  }

  /** Handles the successful sign-up new user */
  @Override
  public void onSuccess() {
    if (createInvoiceView != null) {
      createInvoiceView.switchToHome();
    }
  }
  /** Handles failure during sign-up of new user */
  @Override
  public void onFail() {
    if (createInvoiceView != null) {
      createInvoiceView.sendInvalidInvoiceMessage();
    }
  }
}
