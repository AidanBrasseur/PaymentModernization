package com.example.paymentmodernization.InvoicesHomePage;

import java.util.ArrayList;

public class InvoicesPresenter implements InvoicesInteractor.OnInvoicesFinishedListener {

  private HomeFragment homeFragment;
  private InvoicesInteractor invoicesInteractor;

  InvoicesPresenter(HomeFragment homeFragment, InvoicesInteractor invoicesInteractor) {
    this.homeFragment = homeFragment;
    this.invoicesInteractor = invoicesInteractor;
  }

  public void invoices(String authToken) {
    invoicesInteractor.invoiceDisplay(authToken, this);
  }

  /** Handles the successful retrieval of invoice information */
  @Override
  public void onSuccess(ArrayList<Invoice> invoices) {
    // TODO implement this method call some method on homepageview
    homeFragment.updateInvoices(invoices);
  }
}
