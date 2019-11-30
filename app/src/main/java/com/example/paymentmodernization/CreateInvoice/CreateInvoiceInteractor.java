package com.example.paymentmodernization.CreateInvoice;

import android.text.TextUtils;

import com.example.paymentmodernization.PaymentModernizationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CreateInvoiceInteractor {

  public void createInvoice(
      String authToken,
      String business,
      String deliveryPerson,
      String invoiceDate,
      String dueDate,
      String items,
      final CreateInvoiceInteractor.OnCreateFinishedListener listener) {
    if (TextUtils.isEmpty(business)) {
      listener.onBusinessError("Cannot have empty business");
      return;
    }
    if (TextUtils.isEmpty(dueDate)) {
      listener.onBusinessError("Cannot have empty estimated delivery date");
      return;
    }
    if (items.length() <= 12) {
      listener.onItemsError();
      return;
    }
    try {

      Retrofit retrofit =
          new Retrofit.Builder()
              .baseUrl("https://payment-modernization.herokuapp.com/")
              .addConverterFactory(ScalarsConverterFactory.create())
              .build();
      PaymentModernizationAPI paymentModernizationApi =
          retrofit.create(PaymentModernizationAPI.class);

      final Call<String> call =
          paymentModernizationApi.createInvoice(
              authToken, business, deliveryPerson, invoiceDate, dueDate, items);

      call.enqueue(
          new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
              if (!response.isSuccessful()) {
                System.out.println(response.code());
              } else {
                String valid = response.body();
                if (valid.contains("true")) {
                  listener.onSuccess();
                } else {
                  listener.onFail();
                }
              }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
              // TODO: Handle failure of sign-up
            }
          });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  interface OnCreateFinishedListener {
    void onBusinessError(String message);

    void onDueDateError(String message);

    void onItemsError();

    void onSuccess();

    void onFail();
  }
}
