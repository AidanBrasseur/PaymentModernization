package com.example.paymentmodernization.InvoiceDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.paymentmodernization.ui.home.Invoice;
import com.example.paymentmodernization.R;

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
  private SwipeRefreshLayout refresh;



  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_invoice_details);

    this.invoice = getIntent().getParcelableExtra("invoice");
    this.userType = getIntent().getStringExtra("userType");
    this.authToken = getIntent().getStringExtra("authToken");
    this.refresh = findViewById(R.id.swipe_details);

    refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        getInvoiceDetails();
        refresh.setRefreshing(false);
      }
    });

    System.out.println(invoice.getBusinessAddress().getCity());

    this.business = findViewById(R.id.business);

    this.supplier = findViewById(R.id.supplier);

    this.dueDate = findViewById(R.id.dueDate);

    this.invoiceId = findViewById(R.id.invoiceId);

    this.invoiceDate = findViewById(R.id.invoiceDate);

    this.deliveryDate = findViewById(R.id.deliveryDate);

    this.paymentDate = findViewById(R.id.paymentDate);

    this.itemDescription = findViewById(R.id.itemDescription);
    //System.out.println(invoice.getItems().get(1).getDescription());

    this.itemQuantity = findViewById(R.id.itemQuantity);

    this.itemUnitPrice = findViewById(R.id.itemUnitPrice);

    this.status = findViewById(R.id.status);


    this.button = findViewById(R.id.statusButton);


    this.presenter = new InvoiceDetailsPresenter(this, new InvoiceDetailsInteractor());

    setTextFields(invoice);


  }

  public void getInvoiceDetails(){
   presenter.getInvoiceDetails(invoice.getInvoiceId(), authToken);

  }



  public void setTextFields(Invoice invoice){
    business.setText(invoice.getBusiness());
    supplier.setText(invoice.getSupplier());
    dueDate.setText(invoice.getDueDate());
    invoiceId.setText(invoice.getInvoiceId());
    invoiceDate.setText(invoice.getInvoiceDate());
    deliveryDate.setText(invoice.getDeliveryDate());
    paymentDate.setText(invoice.getPaymentDate());
    paymentDate.setText(invoice.getPaymentDate());
    itemDescription.setText(invoice.getItems().get(0).getDescription());
    itemQuantity.setText(invoice.getItems().get(0).getQuantity());
    itemUnitPrice.setText(invoice.getItems().get(0).getPrice());
    status.setText(invoice.getStatus());
    button.setText(invoice.getStatus());
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
