package com.example.paymentmodernization.HomePage;

import java.util.ArrayList;

public class InvoicesPresenter implements InvoicesInteractor.OnInvoicesFinishedListener {

  /** The LoginView that this LoginPresenter formats information for */
  private InvoicesView invoicesView;
  /** The LoginInteractor that this LoginPresenter retrieves data from */
  private InvoicesInteractor invoicesInteractor;

  public InvoicesPresenter(InvoicesView invoicesView, InvoicesInteractor invoicesInteractor) {
    this.invoicesView = invoicesView;
    this.invoicesInteractor = invoicesInteractor;
  }

  public void invoices(String authToken) {
    invoicesInteractor.invoiceDisplay(authToken, this);
  }

  /** Handles the successful retreival of invoice information */
  @Override
  public void onSuccess(ArrayList<Invoice> invoices) {
    // TODO impliment this method call some method on homepageview
    invoicesView.addInvoiceCards(invoices);
    System.out.println(invoices.toString());
  }


}
