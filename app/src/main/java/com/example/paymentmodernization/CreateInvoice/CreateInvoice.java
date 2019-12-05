package com.example.paymentmodernization.CreateInvoice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.MainActivity.NavDrawer;
import com.example.paymentmodernization.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/** Activity to display create invoice page. Implements CreateInvoiceView */
public class CreateInvoice extends AppCompatActivity implements CreateInvoiceView {
  private EditText businessText;
  private EditText deliveryPersonText;
  private EditText dueDateText;
  private Button submitButton;
  private ProgressBar progressBar;
  private UserInformation userInformation;
  private CreateInvoicePresenter createInvoicePresenter;
  private Calendar myCalendar;
  private TableLayout tableLayout;
  private FloatingActionButton addItemFab;
  private TextView totalPrice;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_invoice);
    Intent intent = getIntent();
    userInformation = intent.getParcelableExtra("userInformation");
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitle("Create Invoice");
    setSupportActionBar(toolbar);
    businessText = findViewById(R.id.businessRecipient);
    deliveryPersonText = findViewById(R.id.deliveryPerson);
    dueDateText = findViewById(R.id.estimatedDate);
    dueDateText.setInputType(InputType.TYPE_NULL);
    progressBar = findViewById(R.id.progress);
    tableLayout = findViewById(R.id.items_table);
    addItemRow();
    addItemFab = findViewById(R.id.fab);
    totalPrice = findViewById(R.id.totalPrice);
    addItemFab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            addItemRow();
          }
        });
    createInvoicePresenter = new CreateInvoicePresenter(this, new CreateInvoiceInteractor());
    submitButton = findViewById(R.id.submit);
    myCalendar = Calendar.getInstance();

    final TimePickerDialog.OnTimeSetListener time =
        new TimePickerDialog.OnTimeSetListener() {
          @Override
          public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            updateLabel();
          }
        };

    final DatePickerDialog.OnDateSetListener date =
        new DatePickerDialog.OnDateSetListener() {

          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            new TimePickerDialog(
                    CreateInvoice.this,
                    time,
                    myCalendar.get(Calendar.HOUR_OF_DAY),
                    myCalendar.get(Calendar.MINUTE),
                    false)
                .show();
          }
        };

    dueDateText.setOnFocusChangeListener(
        new View.OnFocusChangeListener() {
          @Override
          public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
              new DatePickerDialog(
                      CreateInvoice.this,
                      date,
                      myCalendar.get(Calendar.YEAR),
                      myCalendar.get(Calendar.MONTH),
                      myCalendar.get(Calendar.DAY_OF_MONTH))
                  .show();
            }
          }
        });
    submitButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            try {
              JSONArray items = new JSONArray();
              for (int i = 1; i < tableLayout.getChildCount(); i++) {
                View tableView = tableLayout.getChildAt(i);
                if (tableView instanceof TableRow) {
                  JSONObject tempItem = new JSONObject();
                  TableRow row = (TableRow) tableView;
                  String desc = ((EditText) row.getChildAt(0)).getText().toString();
                  String quantity = ((EditText) row.getChildAt(1)).getText().toString();
                  String price = ((EditText) row.getChildAt(2)).getText().toString();
                  if (!TextUtils.isEmpty(desc)
                      && !TextUtils.isEmpty(quantity)
                      && !TextUtils.isEmpty(price)) {
                    tempItem.put("description", desc);
                    tempItem.put("quantity", quantity);
                    tempItem.put("price", price);
                    items.put(tempItem);
                  }
                }
              }
              JSONObject itemsJson = new JSONObject();
              itemsJson.put("items", items);
              createInvoice(
                  userInformation.getAuthToken(),
                  businessText.getText().toString(),
                  deliveryPersonText.getText().toString(),
                  dueDateText.getText().toString(),
                  itemsJson.toString());
            } catch (Exception e) {
              // TODO: thing
            }
          }
        });
  }
  /** updates due date text with date selected by the user */
  private void updateLabel() {
    String myFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
    dueDateText.setText(sdf.format(myCalendar.getTime()));
  }

  /** Shows current loading progress */
  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }
  /** Hides current loading progress */
  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }
  /**
   * Sets error message given an error with the business
   *
   * @param message the message for the error
   */
  @Override
  public void setBusinessError(String message) {
    businessText.setError(message);
  }
  /**
   * Sets error message given an error with the due date
   *
   * @param message the message for the error
   */
  @Override
  public void setDueDateError(String message) {
    dueDateText.setError(message);
  }
  /** switches current screen to the homescreen */
  @Override
  public void switchToHome() {
    Intent intent = new Intent(CreateInvoice.this, NavDrawer.class);
    intent.putExtra("userInformation", userInformation);
    startActivity(intent);
  }
  /** sends message to user indicating that their invoice was invalid */
  @Override
  public void sendInvalidInvoiceMessage() {
    progressBar.setVisibility(View.GONE);
    Toast.makeText(getApplicationContext(), "Invalid Invoice", Toast.LENGTH_LONG).show();
  }
  /** sends message to user indicating that there is an invalid item in the invoice */
  @Override
  public void sendInvalidItemsMessage() {
    progressBar.setVisibility(View.GONE);
    Toast.makeText(
            getApplicationContext(), "Invoice must contain at least one item", Toast.LENGTH_LONG)
        .show();
  }

  /**
   * Attempts to create an invoice with the provided information
   *
   * @param authToken the authorization token of the user
   * @param business the business the invoice is being sent to
   * @param deliveryPerson the delivery person that the invoice is assigned to
   * @param dueDate the due date of the invoice
   * @param items the items associated with the invoice
   */
  void createInvoice(
      String authToken, String business, String deliveryPerson, String dueDate, String items) {
    String myFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
    String date = sdf.format(new Date());
    createInvoicePresenter.createInvoice(authToken, business, deliveryPerson, date, dueDate, items);
  }

  /** Adds a row to the table for a new item */
  void addItemRow() {
    TableRow row =
        (TableRow) LayoutInflater.from(CreateInvoice.this).inflate(R.layout.item_table_row, null);
    final EditText orderPrice = row.findViewById(R.id.orderPrice);
    final EditText orderQuantity = row.findViewById(R.id.orderQt);
    orderQuantity.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateTotalPrice();
          }

          @Override
          public void afterTextChanged(Editable editable) {}
        });
    orderPrice.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateTotalPrice();
          }

          @Override
          public void afterTextChanged(Editable editable) {}
        });
    tableLayout.addView(row);
  }

  /** Updates the total price text field with the current total from the items table */
  void updateTotalPrice() {
    double totalPrice = 0;
    for (int i = 1; i < tableLayout.getChildCount(); i++) {
      View tableView = tableLayout.getChildAt(i);
      if (tableView instanceof TableRow) {
        TableRow row = (TableRow) tableView;
        try {
          String quantity = ((EditText) row.getChildAt(1)).getText().toString();
          String price = ((EditText) row.getChildAt(2)).getText().toString();
          totalPrice += Double.parseDouble(quantity) * Double.parseDouble(price);
        } catch (Exception e) {
        }
      }
    }
    DecimalFormat df = new DecimalFormat("#.00");
    this.totalPrice.setText(String.format("Total Price: $%s", df.format(totalPrice)));
  }

  public void onResume() {
    super.onResume();
    hideProgress();
  }
}
