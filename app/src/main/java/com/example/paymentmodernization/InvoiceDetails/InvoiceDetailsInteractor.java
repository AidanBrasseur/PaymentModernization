package com.example.paymentmodernization.InvoiceDetails;

import com.example.paymentmodernization.InvoicesHomePage.Invoice;
import com.example.paymentmodernization.PaymentModernizationAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * InvoiceDetailsInteractor communicates with server to perform updates and retrieval of data for
 * invoices.
 */
class InvoiceDetailsInteractor {

  /**
   * Attempts to update the status of the invoice to newStatus, calling the corresponding method in
   * listener.
   *
   * @param invoiceId the id of the invoice to be updated
   * @param authToken the authorization token of the user
   * @param newStatus the new status
   * @param listener an OnStatusUpdatedListener to signal success or failure of status updates.
   */
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
  /**
   * Attempts to retrieve details about the status of the invoice
   *
   * @param invoiceId the id of the invoice to be updated
   * @param authToken the authorization token of the user
   * @param listener an OnStatusUpdatedListener to signal success or failure of invoice retrieval.
   */
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
  /**
   * Attempts to update the payment or delivery date of the invoice to newDate, corresponding to
   * userType. Calls the corresponding method in listener.
   *
   * @param invoiceId the id of the invoice to be updated
   * @param authToken the authorization token of the user
   * @param newDate the new date
   * @param userType the type of the user
   * @param listener an OnStatusUpdatedListener to signal success or failure of date updates.
   */
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
              listener.onDateUpdateSuccess();
            } else {

              listener.onDateUpdateFailure();
            }
          }

          @Override
          public void onFailure(Call<String> call, Throwable t) {
            // TODO: handle failure

          }
        });
  }
  /**
   * Attempts to update the driver of the invoice to newDriver, calling the corresponding method in
   * listener.
   *
   * @param invoiceId the id of the invoice to be updated
   * @param authToken the authorization token of the user
   * @param newDriver the new driver
   * @param listener an OnStatusUpdatedListener to signal success or failure of driver updates.
   */
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
              listener.onDriverUpdateSuccess();
            } else {

              listener.onDriverUpdateFailure();
            }
          }

          @Override
          public void onFailure(Call<String> call, Throwable t) {
            // TODO: handle failure

          }
        });
  }
  /**
   * OnStatusUpdatedListener is an interface outlines methods for use during the displaying and
   * update of invoices.
   */
  public interface OnStatusUpdatedListener {
    void onStatusUpdateSuccess(String newStatus);

    void onStatusUpdateFailure(String newStatus);

    void onDriverUpdateSuccess();

    void onDriverUpdateFailure();

    void onDateUpdateSuccess();

    void onDateUpdateFailure();

    void onSuccessDetails(Invoice invoice);
  }
}
