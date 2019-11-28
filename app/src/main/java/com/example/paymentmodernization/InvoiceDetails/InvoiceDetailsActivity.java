package com.example.paymentmodernization.InvoiceDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentmodernization.HomePage.Invoice;
import com.example.paymentmodernization.HomePage.InvoiceItem;
import com.example.paymentmodernization.R;

import java.util.ArrayList;

public class InvoiceDetailsActivity extends AppCompatActivity implements InvoiceDetailsView {

  private InvoiceDetailsPresenter presenter;
  private Invoice invoice;
  private String userType;
  private String authToken;

  private Button button;
  private TextView business;
  private TextView supplier;
  private TextView dueDate;
  private TextView invoiceId;
  private TextView invoiceDate;
  private TextView deliveryDate;
  private TextView paymentDate;
  private TextView itemDescription;
  private TextView itemQuantity;
  private TextView itemUnitPrice;
  private TextView status;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_invoice_details);

    this.invoice = getIntent().getParcelableExtra("invoice");
    this.userType = getIntent().getStringExtra("userType");
    this.authToken = getIntent().getStringExtra("authToken");

    this.business = findViewById(R.id.business);
    business.setText(invoice.getBusiness());
    this.supplier = findViewById(R.id.supplier);
    supplier.setText(invoice.getInvoiceId());
    this.dueDate = findViewById(R.id.dueDate);
    dueDate.setText(invoice.getDueDate());
    this.invoiceId = findViewById(R.id.invoiceId);
    invoiceId.setText(invoice.getInvoiceId());
    this.invoiceDate = findViewById(R.id.invoiceDate);
    invoiceDate.setText(invoice.getInvoiceDate());
    this.deliveryDate = findViewById(R.id.deliveryDate);
    deliveryDate.setText(invoice.getDeliveryDate());
    this.paymentDate = findViewById(R.id.paymentDate);
    paymentDate.setText(invoice.getPaymentDate());
    this.itemDescription = findViewById(R.id.itemDescription);
    itemDescription.setText(invoice.getItems().get(0).getDescription());
    this.itemQuantity = findViewById(R.id.itemQuantity);
    itemQuantity.setText(invoice.getItems().get(0).getQuantity());
    this.itemUnitPrice = findViewById(R.id.itemUnitPrice);
    itemUnitPrice.setText(invoice.getItems().get(0).getPrice());
    this.status = findViewById(R.id.status);
    status.setText(invoice.getStatus());

    this.button = findViewById(R.id.statusButton);
    button.setText(invoice.getStatus());

    presenter = new InvoiceDetailsPresenter(this, new InvoiceDetailsInteractor());

    // checking user type and displaying correct button
    if (invoice.getStatus().equals("COMPLETE")) {
      button.setText(("Completed"));
      button.setEnabled(false);
    } else {
      if (userType.equals("DELIVERY_PERSON")) {
        if (invoice.getStatus().equals("DELIVERED")) {
          button.setText(("Delivered"));
          button.setEnabled(false);
        } else {
          button.setText(("Deliver"));
        }
      } else if (userType.equals("SUPPLIER")) {
        button.setEnabled(false);
        button.setVisibility(View.INVISIBLE);
      } else if (userType.equals("SMALL_BUSINESS")) {
        if (invoice.getStatus().equals("PAID")) {
          button.setText(("Paid"));
          button.setEnabled(false);
        } else {
          button.setText(("Pay"));
        }
      }
      button.setOnClickListener(
          new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              handleStatusButtonClick();
            }
          });
    }
  }

  public void handleStatusButtonClick() {
    System.out.println("button clicked******************************");
    if (userType.equals("DELIVERY_PERSON")) {
      if (invoice.getStatus().equals("PAID")) {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "COMPLETE");
      } else {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "DELIVERED");
      }
    } else if (userType.equals("SMALL_BUSINESS")) {
      if (invoice.getStatus().equals("DELIVERED")) {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "COMPLETE");
      } else {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "PAID");
      }
    }
  }

  public void statusUpdatedSuccess(String newStatus) {
    button.setText(newStatus);
    if (newStatus.equals("DELIVERED") || newStatus.equals("PAID") || newStatus.equals("COMPLETE")) {
      button.setEnabled(false);
    }
  }

  @Override
  public void onBackPressed() {
    finish();
  }
}
