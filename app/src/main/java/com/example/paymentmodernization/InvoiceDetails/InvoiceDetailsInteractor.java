package com.example.paymentmodernization.InvoiceDetails;

import com.example.paymentmodernization.PaymentModernizationAPI;
import com.example.paymentmodernization.ui.home.Invoice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
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
              listener.onStatusUpdateSuccess(newStatus);
            } else {

              listener.onStatusUpdateFailure(newStatus);
            }
          }

          @Override
          public void onFailure(Call<String> call, Throwable t) {
            // TODO: handle failure

          }
        });
  }

  public void getInvoiceDetails(
      String invoiceId, String authToken, final OnStatusUpdatedListener listener) {
    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl("https://payment-modernization.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    PaymentModernizationAPI paymentModernizationAPI =
        retrofit.create(PaymentModernizationAPI.class);
    final Call<Invoice> call = paymentModernizationAPI.getInvoice(invoiceId, authToken);
    call.enqueue(
        new Callback<Invoice>() {
          @Override
          public void onResponse(Call<Invoice> call, Response<Invoice> response) {
            Invoice invoice = response.body();
            listener.onSuccessDetails(invoice);
          }

          @Override
          public void onFailure(Call<Invoice> call, Throwable t) {
            // TODO onfailure
          }
        });
  }

  public void updateDate(
      String invoiceId,
      String authToken,
      final String newDate,
      String userType,
      final OnStatusUpdatedListener listener) {

    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl("https://payment-modernization.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    PaymentModernizationAPI paymentModernizationAPI =
        retrofit.create(PaymentModernizationAPI.class);
    final Call<String> call;
    if (userType.equals("DELIVERY_PERSON"))
      call = paymentModernizationAPI.updateDeliveryDate(invoiceId, authToken, newDate);
    else call = paymentModernizationAPI.updatePaymentDate(invoiceId, authToken, newDate);
    call.enqueue(
        new Callback<String>() {
          @Override
          public void onResponse(Call<String> call, Response<String> response) {
            System.out.println(response.isSuccessful());
            System.out.println(response.code());
            String valid = response.body();
            if (valid.contains("true")) {
              listener.onDateUpdateSuccess(newDate);
            } else {

              listener.onDateUpdateFailure(newDate);
            }
          }

          @Override
          public void onFailure(Call<String> call, Throwable t) {
            // TODO: handle failure

          }
        });
  }

  public void updateDriver(
      String invoiceId,
      String authToken,
      final String newDriver,
      final OnStatusUpdatedListener listener) {

    Retrofit retrofit =
        new Retrofit.Builder()
            .baseUrl("https://payment-modernization.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build();

    PaymentModernizationAPI paymentModernizationAPI =
        retrofit.create(PaymentModernizationAPI.class);

    final Call<String> call = paymentModernizationAPI.updateDriver(invoiceId, authToken, newDriver);

    call.enqueue(
        new Callback<String>() {
          @Override
          public void onResponse(Call<String> call, Response<String> response) {
            System.out.println(response.isSuccessful());
            System.out.println(response.code());
            String valid = response.body();
            if (valid.contains("true")) {
              listener.onDriverUpdateSuccess(newDriver);
            } else {

              listener.onDriverUpdateFailure(newDriver);
            }
          }

          @Override
          public void onFailure(Call<String> call, Throwable t) {
            // TODO: handle failure

          }
        });
  }

  public interface OnStatusUpdatedListener {
    void onStatusUpdateSuccess(String newStatus);

    void onStatusUpdateFailure(String newStatus);

    void onDriverUpdateSuccess(String newDriver);

    void onDriverUpdateFailure(String newDriver);

    void onDateUpdateSuccess(String newDate);

    void onDateUpdateFailure(String newDate);

    void onSuccessDetails(Invoice invoice);
  }
}
