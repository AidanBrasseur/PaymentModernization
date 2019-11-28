package com.example.paymentmodernization.InvoiceDetails;

import com.example.paymentmodernization.PaymentModernizationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InvoiceDetailsInteractor {

  public void updateStatus(
      String invoiceId,
      String authToken,
      final String newStatus,
      final OnStatusUpdatedListener listener) {

    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl("https://payment-modernization.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    PaymentModernizationAPI paymentModernizationAPI =
        retrofit.create(PaymentModernizationAPI.class);

    final Call<String> call = paymentModernizationAPI.updateStatus(invoiceId, authToken, newStatus);

    call.enqueue(
        new Callback<String>() {
          @Override
          public void onResponse(Call<String> call, Response<String> response) {
            System.out.println(response.isSuccessful());
            System.out.println(response.code());
            String valid = response.body();
            if (valid.contains("true")) {
              listener.onSuccess(newStatus);
            } else {

              listener.onFailure(newStatus);
            }
          }

          @Override
          public void onFailure(Call<String> call, Throwable t) {
            // TODO: handle failure

          }
        });
  }

  public interface OnStatusUpdatedListener {
    void onSuccess(String newStatus);

    void onFailure(String newStatus);
  }
}
