package com.example.paymentmodernization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PaymentModernizationApi {

    @GET("Login")
    Call<LoginAuthorization> getLoginAuthorization(@Header("Authorization") String authorizationString);
}
