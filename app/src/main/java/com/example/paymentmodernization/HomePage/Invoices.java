package com.example.paymentmodernization.HomePage;

import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;

/** An object to store invoices of the user information */
public class Invoices {


    /** The invoices for this user */
    private ArrayList<Invoice> invoices;

    /**
     * get the invoices
     * @return invoices
     */
    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    /**
     * set invoices
     * @param invoices
     */
    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

}