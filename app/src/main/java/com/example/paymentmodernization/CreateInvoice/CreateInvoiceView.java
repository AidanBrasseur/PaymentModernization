package com.example.paymentmodernization.CreateInvoice;

public interface CreateInvoiceView {
  /** Shows current loading progress */
  void showProgress();
  /** Hides current loading progress */
  void hideProgress();
  /**
   * Sets error message given an error with the username
   *
   * @param message the message for the error
   */
  void setBusinessError(String message);
  /**
   * Sets error message given an error with the password
   *
   * @param message the message for the error
   */
  void setDueDateError(String message);
  /** switches current screen to the login screen */
  void switchToHome();
  /** sends message to user indicating that their sign-up attempt was invalid */
  void sendInvalidInvoiceMessage();

  void setItemsError(String message);
}
