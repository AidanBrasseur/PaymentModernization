package com.example.paymentmodernization.InvoiceDetails;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentmodernization.HomePage.Invoice;
import com.example.paymentmodernization.R;

public class InvoiceDetailsActivity extends AppCompatActivity implements InvoiceDetailsView {

  private InvoiceDetailsPresenter presenter;
  private Invoice invoice;
  private TextView tmpText;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_invoice_details);
    this.invoice = (Invoice) getIntent().getSerializableExtra("invoice");
    this.tmpText = findViewById(R.id.tmp);
  }
}
