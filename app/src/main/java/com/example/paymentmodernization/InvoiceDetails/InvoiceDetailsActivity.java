package com.example.paymentmodernization.InvoiceDetails;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.paymentmodernization.InvoicesHomePage.Address;
import com.example.paymentmodernization.InvoicesHomePage.Invoice;
import com.example.paymentmodernization.InvoicesHomePage.InvoiceItem;
import com.example.paymentmodernization.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** Activity to display invoice details page. Implements InvoiceDetailsView. */
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
  private TextView totalPriceLarge;
  private TextView deliveryDriver;
  private TextView supplierStreet;
  private TextView supplierRegionAndPostalCode;
  private TextView businessStreet;
  private TextView businessRegionAndPostalCode;
  private ProgressBar progressBar;

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

    this.progressBar = findViewById(R.id.progress);

    this.business = findViewById(R.id.recipient_name);

    this.supplier = findViewById(R.id.supplier);

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

  /** Retrieves most updated information about the invoice */
  private void getInvoiceDetails() {
    presenter.getInvoiceDetails(invoice.getInvoiceId(), authToken);
  }

  /**
   * Sets text on page to display the information provided in invoice
   *
   * @param invoice the invoice to be displayed
   */
  public void setTextFields(Invoice invoice) {
    this.invoice = invoice;
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
    TableRow row =
        (TableRow)
            LayoutInflater.from(InvoiceDetailsActivity.this)
                .inflate(R.layout.total_price_row, null);
    DecimalFormat df = new DecimalFormat("#.00");
    TextView totalPriceText = row.findViewById(R.id.totalPrice);
    totalPriceText.setText(String.format("Total Price:     $%s", df.format(totalPrice)));
    itemTable.addView(row);
    this.totalPriceLarge.setText(String.format("$%s", df.format(totalPrice)));

    business.setText(invoice.getBusiness());
    if (invoice.getDeliveryPerson() != null) deliveryDriver.setText(invoice.getDeliveryPerson());
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
        button.setText("Update Driver");
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
              if (userType.equals("SUPPLIER")) {
                handleDriverUpdateButton();
              } else {
                handleStatusButtonClick();
              }
            }
          });
    }
  }
  /** Handles successful update of date */
  @Override
  public void dateUpdatedSuccess() {}
  /** Handles successful update of driver */
  @Override
  public void driverUpdatedSuccess() {
    getInvoiceDetails();
  }
  /** Handles the choice of status to be updated on the invoice */
  private void handleStatusButtonClick() {
    String myFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
    String date = sdf.format(new Date());
    if (userType.equals("DELIVERY_PERSON")) {
      presenter.updateDate(invoice.getInvoiceId(), authToken, date, userType);
      if (invoice.getStatus().equals("PAID")) {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "COMPLETE");
      } else {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "DELIVERED");
      }
    } else if (userType.equals("SMALL_BUSINESS")) {
      presenter.updateDate(invoice.getInvoiceId(), authToken, date, userType);
      if (invoice.getStatus().equals("DELIVERED")) {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "COMPLETE");
      } else {
        presenter.updateStatus(invoice.getInvoiceId(), authToken, "PAID");
      }
    }
  }
  /** Handles successful update of status */
  public void statusUpdatedSuccess(String newStatus) {
    button.setText(newStatus);
    if (newStatus.equals("DELIVERED") || newStatus.equals("PAID") || newStatus.equals("COMPLETE")) {
      button.setEnabled(false);
    }
    getInvoiceDetails();
  }

  @Override
  public void onBackPressed() {
    finish();
  }
  /** Handles dialog to prompt update of driver */
  private void handleDriverUpdateButton() {
    final EditText driverEditText = new EditText(this);
    driverEditText.setHint("Driver");
    AlertDialog dialog =
        new AlertDialog.Builder(this)
            .setTitle("Update Driver")
            .setView(driverEditText)
            .setPositiveButton(
                "Update",
                new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                    presenter.updateDriver(
                        invoice.getInvoiceId(),
                        authToken,
                        String.valueOf(driverEditText.getText()));
                  }
                })
            .setNegativeButton("Cancel", null)
            .create();
    dialog.show();
  }
}
