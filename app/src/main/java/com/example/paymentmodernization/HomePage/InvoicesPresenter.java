package com.example.paymentmodernization.HomePage;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class InvoicesPresenter implements InvoicesInteractor.OnInvoicesFinishedListener {

  /** The LoginView that this LoginPresenter formats information for */
  private HomePageView homePageView;
  /** The LoginInteractor that this LoginPresenter retrieves data from */
  private InvoicesInteractor invoicesInteractor;

  public InvoicesPresenter(HomePageView homePageView, InvoicesInteractor invoicesInteractor) {
    this.homePageView = homePageView;
    this.invoicesInteractor = invoicesInteractor;
  }

  public void invoices(String authToken) {
    invoicesInteractor.invoiceDisplay(authToken, this);
  }

  /** Handles the successful retreival of invoice information */
  @Override
  public void onSuccess(ArrayList<LinkedTreeMap> invoices) {
    // TODO impliment this method call some method on homepageview
    homePageView.addInvoicesToTable(invoices);
    System.out.println(invoices.toString());
  }
}
