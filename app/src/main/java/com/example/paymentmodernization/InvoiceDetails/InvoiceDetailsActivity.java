package com.example.paymentmodernization.InvoiceDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.paymentmodernization.HomePage.Invoice;
import com.example.paymentmodernization.R;

public class InvoiceDetailsActivity extends AppCompatActivity implements InvoiceDetailsView {

  private InvoiceDetailsPresenter presenter;
  private Invoice invoice;
  private String userType;
  private String authToken;
  private TextView tmpText;
  private Button button;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_invoice_details);

    this.invoice = getIntent().getParcelableExtra("invoice");
    this.userType = getIntent().getStringExtra("userType");
    this.authToken = getIntent().getStringExtra("authToken");

    this.tmpText = findViewById(R.id.tmp);
    this.button = findViewById(R.id.statusButton);
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
