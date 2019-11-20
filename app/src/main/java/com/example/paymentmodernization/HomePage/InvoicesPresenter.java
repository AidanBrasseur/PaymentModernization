package com.example.paymentmodernization.HomePage;

import com.example.paymentmodernization.HomePage.InvoicesInteractor;
import com.example.paymentmodernization.Login.LoginView;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;

public class InvoicesPresenter implements InvoicesInteractor.OnInvoicesFinishedListener{

    /** The LoginView that this LoginPresenter formats information for */
    private HomePageView homePageView;
    /** The LoginInteractor that this LoginPresenter retrieves data from */
    private InvoicesInteractor invoicesInteractor;


    public InvoicesPresenter(HomePageView homePageView, InvoicesInteractor invoicesInteractor) {
        this.homePageView = homePageView;
        this.invoicesInteractor = invoicesInteractor;
    }

    public void invoices(String authToken){
        System.out.println("*****************************");
        invoicesInteractor.invoiceDisplay(authToken, this);
    }

    /** Handles the successful validation of login information */
    @Override
    public void onSuccess(ArrayList<LinkedTreeMap> invoices) {
    // TODO impliment this method call some method on homepageview
        homePageView.showInvoices(invoices);
    }
}
