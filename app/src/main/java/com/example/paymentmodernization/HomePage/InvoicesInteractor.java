package com.example.paymentmodernization.HomePage;

import android.text.TextUtils;
import android.util.Base64;

import com.example.paymentmodernization.PaymentModernizationAPI;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InvoicesInteractor {

    public void invoiceDisplay(String authorizationString, final OnInvoicesFinishedListener listener){
        try{
      System.out.println(authorizationString + "***************************");
            Retrofit retrofit =
                    new Retrofit.Builder()
                            .baseUrl("https://payment-modernization.herokuapp.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

            PaymentModernizationAPI paymentModernizationApi =
                    retrofit.create(PaymentModernizationAPI.class);
            final Call<Invoices> call =
                    paymentModernizationApi.getInvoices(authorizationString);

      call.enqueue(
          new Callback<Invoices>() {

            @Override
            public void onResponse(Call<Invoices> call, Response<Invoices> response) {
              if (!response.isSuccessful()) {
                System.out.println("not succesfful *************");
              } else {
                Invoices invoices = response.body();
                listener.onSuccess(invoices.getInvoices());
               // System.out.println("*********************interactotr********");
               // System.out.println(invoices.getInvoices().get(0).get("business"));
              }
            }

            @Override
            public void onFailure(Call<Invoices> call, Throwable t) {
              // TODO: Handle failure of http get
              System.out.println(t.toString() + "**FAILURE FUNCTION*******");
            }
          });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    /** OnInvoicesFinishedListener is an interface outlines methods for use during homePage loads. */
    interface OnInvoicesFinishedListener {

        void onSuccess(ArrayList<LinkedTreeMap> invoices);
    }

}
