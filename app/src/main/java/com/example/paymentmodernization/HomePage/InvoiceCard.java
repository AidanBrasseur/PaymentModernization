package com.example.paymentmodernization.HomePage;

import android.widget.TextView;

public class InvoiceCard {
    private String supplierToCompany;
    private String delivery;


    public InvoiceCard(String supplierToCompany, String delivery){
        this.supplierToCompany = supplierToCompany;
        this.delivery = delivery;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getSupplierToCompany() {
        return supplierToCompany;
    }
}
