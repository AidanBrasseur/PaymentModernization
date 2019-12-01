package com.example.paymentmodernization.ui.home;

import com.example.paymentmodernization.HomePage.InvoicesView;

import java.util.ArrayList;

public class InvoicesPresenter implements InvoicesInteractor.OnInvoicesFinishedListener {

  /** The LoginView that this LoginPresenter formats information for */
  private HomeFragment homeFragment;
  /** The LoginInteractor that this LoginPresenter retrieves data from */
  private InvoicesInteractor invoicesInteractor;

  public InvoicesPresenter(HomeFragment homeFragment, InvoicesInteractor invoicesInteractor) {
    this.homeFragment = homeFragment;
    this.invoicesInteractor = invoicesInteractor;
  }

  public void invoices(String authToken) {
    invoicesInteractor.invoiceDisplay(authToken, this);
  }

  /** Handles the successful retreival of invoice information */
  @Override
  public void onSuccess(ArrayList<Invoice> invoices) {
    // TODO impliment this method call some method on homepageview
    homeFragment.updateInvoices(invoices);
    System.out.println(invoices.toString());
  }
}
