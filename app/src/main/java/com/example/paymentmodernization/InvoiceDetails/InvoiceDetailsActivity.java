package com.example.paymentmodernization.InvoiceDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.paymentmodernization.R;
import com.example.paymentmodernization.ui.home.Address;
import com.example.paymentmodernization.ui.home.Invoice;
import com.example.paymentmodernization.ui.home.InvoiceItem;

import java.text.DecimalFormat;

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
  private TextView status;
  private SwipeRefreshLayout refresh;
  private TableLayout itemTable;
  private TextView totalPrice;
  private TextView totalPriceLarge;
  private TextView deliveryDriver;
  private TextView supplierStreet;
  private TextView supplierRegionAndPostalCode;
  private TextView businessStreet;
  private TextView businessRegionAndPostalCode;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_invoice_details);
    Toolbar toolbar = findViewById(R.id.toolbar);

    this.invoice = getIntent().getParcelableExtra("invoice");
    this.userType = getIntent().getStringExtra("userType");
    this.authToken = getIntent().getStringExtra("authToken");
    this.refresh = findViewById(R.id.swipe_details);
    this.itemTable = findViewById(R.id.items_table);
    this.totalPriceLarge = findViewById(R.id.total_price_large);
    this.deliveryDriver = findViewById(R.id.delivery_driver_name);
    this.businessStreet = findViewById(R.id.recipient_street_address);
    this.businessRegionAndPostalCode = findViewById(R.id.recipient_region_postal);
    toolbar.setTitle("Invoice #" + invoice.getInvoiceId());
    setSupportActionBar(toolbar);
    refresh.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            getInvoiceDetails();
            refresh.setRefreshing(false);
          }
        });

    this.business = findViewById(R.id.recipient_name);

    this.supplier = findViewById(R.id.supplier);
    this.totalPrice = findViewById(R.id.totalPrice);

    this.dueDate = findViewById(R.id.due_date);

    this.invoiceId = findViewById(R.id.invoice_id);

    this.invoiceDate = findViewById(R.id.invoice_date);
    this.supplierStreet = findViewById(R.id.supplier_streetaddress);
    this.supplierRegionAndPostalCode = findViewById(R.id.supplier_region_postal);

    this.deliveryDate = findViewById(R.id.delivery_date);

    this.paymentDate = findViewById(R.id.payment_date);

    this.status = findViewById(R.id.status);

    this.button = findViewById(R.id.statusButton);

    this.presenter = new InvoiceDetailsPresenter(this, new InvoiceDetailsInteractor());

    setTextFields(invoice);
  }

  public void getInvoiceDetails() {
    presenter.getInvoiceDetails(invoice.getInvoiceId(), authToken);
  }

  public void setTextFields(Invoice invoice) {
    double totalPrice = 0;
    while (itemTable.getChildCount() > 1)
      itemTable.removeView(itemTable.getChildAt(itemTable.getChildCount() - 1));
    for (InvoiceItem invoiceItem : this.invoice.getItems()) {
      TableRow row =
          (TableRow)
              LayoutInflater.from(InvoiceDetailsActivity.this)
                  .inflate(R.layout.invoice_items_table_row, null);
      TextView orderDesc = row.findViewById(R.id.orderDesc);
      TextView orderQty = row.findViewById(R.id.orderQt);
      TextView orderPrice = row.findViewById(R.id.orderPrice);
      orderDesc.setText(invoiceItem.getDescription());
      orderQty.setText(invoiceItem.getQuantity());
      orderPrice.setText(invoiceItem.getPrice());
      totalPrice +=
          Double.parseDouble(invoiceItem.getPrice())
              * Double.parseDouble(invoiceItem.getQuantity());
      itemTable.addView(row);
    }
    DecimalFormat df = new DecimalFormat("#.##");
    this.totalPriceLarge.setText(String.format("$%s", df.format(totalPrice)));
    this.totalPrice.setText(String.format("Total Price: $%s", df.format(totalPrice)));
    business.setText(invoice.getBusiness());
    deliveryDriver.setText(invoice.getDriver());
    Address supplierAddress = invoice.getSupplierAddress();
    supplierStreet.setText(supplierAddress.getStreetAddress());
    supplierRegionAndPostalCode.setText(
        String.format(
            "%s, %s, %s, %s",
            supplierAddress.getCity(),
            supplierAddress.getRegion(),
            supplierAddress.getCountry(),
            supplierAddress.getPostalCode()));
    Address businessAddress = invoice.getBusinessAddress();
    businessStreet.setText(businessAddress.getStreetAddress());
    businessRegionAndPostalCode.setText(
        String.format(
            "%s, %s, %s, %s",
            businessAddress.getCity(),
            businessAddress.getRegion(),
            businessAddress.getCountry(),
            businessAddress.getPostalCode()));
    supplier.setText(invoice.getSupplier());

    dueDate.setText(invoice.getDueDate());
    invoiceId.setText(invoice.getInvoiceId());
    invoiceDate.setText(invoice.getInvoiceDate());
    if (invoice.getDeliveryDate() != null) deliveryDate.setText(invoice.getDeliveryDate());
    if (invoice.getPaymentDate() != null) paymentDate.setText(invoice.getPaymentDate());

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
