package com.example.paymentmodernization.InvoicesHomePage;

import com.example.paymentmodernization.PaymentModernizationAPI;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * interactor to retreive dinfo from backend about invoices for specific user
 */
class InvoicesInteractor {

  void invoiceDisplay(String authorizationString, final OnInvoicesFinishedListener listener) {
    try {
      Retrofit retrofit =
          new Retrofit.Builder()
              .baseUrl("https://payment-modernization.herokuapp.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();

      PaymentModernizationAPI paymentModernizationApi =
          retrofit.create(PaymentModernizationAPI.class);
      final Call<Invoices> call = paymentModernizationApi.getInvoices(authorizationString);

      call.enqueue(
          new Callback<Invoices>() {

            @Override
            public void onResponse(Call<Invoices> call, Response<Invoices> response) {
              if (!response.isSuccessful()) {
              } else {
                Invoices invoices = response.body();
                if (invoices == null) {}
                if (invoices.getInvoices() != null) listener.onSuccess(invoices.getInvoices());
              }
            }

            @Override
            public void onFailure(Call<Invoices> call, Throwable t) {
              // TODO: Handle failure of http get.
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** OnInvoicesFinishedListener is an interface outlines methods for use during homePage loads. */
  interface OnInvoicesFinishedListener {

    void onSuccess(ArrayList<Invoice> invoices);
  }
}
