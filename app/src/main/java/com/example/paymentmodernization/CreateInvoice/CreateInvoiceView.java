package com.example.paymentmodernization.CreateInvoice;

/** An interface for the methods necessary for creation of invoices */
public interface CreateInvoiceView {
  /** Shows current loading progress */
  void showProgress();
  /** Hides current loading progress */
  void hideProgress();
  /**
   * Sets error message given an error with the business
   *
   * @param message the message for the error
   */
  void setBusinessError(String message);
  /**
   * Sets error message given an error with the due date
   *
   * @param message the message for the error
   */
  void setDueDateError(String message);
  /** switches current screen to the home screen */
  void switchToHome();
  /** sends message to user indicating that their invoice was invalid */
  void sendInvalidInvoiceMessage();
  /** sends message to user indicating that there is an invalid item in the invoice */
  void sendInvalidItemsMessage();
}
