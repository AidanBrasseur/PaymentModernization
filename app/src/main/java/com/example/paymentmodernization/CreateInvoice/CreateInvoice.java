package com.example.paymentmodernization.CreateInvoice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
  private double currPrice = 0.0;

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
            // TODO Auto-generated method stub
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
            // TODO Auto-generated method stub
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
                  EditText desc = (EditText) row.getChildAt(0);
                  EditText quantity = (EditText) row.getChildAt(1);
                  EditText price = (EditText) row.getChildAt(2);
                  tempItem.put("description", desc.getText().toString());
                  tempItem.put("quantity", quantity.getText().toString());
                  tempItem.put("price", price.getText().toString());
                  items.put(tempItem);
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

  private void updateLabel() {
    String myFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
    dueDateText.setText(sdf.format(myCalendar.getTime()));
  }

  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override
  public void setBusinessError(String message) {
    businessText.setError(message);
  }

  @Override
  public void setDueDateError(String message) {
    dueDateText.setError(message);
  }

  @Override
  public void switchToHome() {
    Intent intent = new Intent(CreateInvoice.this, NavDrawer.class);
    intent.putExtra("userInformation", userInformation);
    startActivity(intent);
  }

  @Override
  public void sendInvalidInvoiceMessage() {
    progressBar.setVisibility(View.GONE);
    Toast.makeText(getApplicationContext(), "Invalid Invoice", Toast.LENGTH_LONG).show();
  }

  @Override
  public void setItemsError(String message) {
    // TODO: implement proper items stuff
  }

  void createInvoice(
      String authToken, String business, String deliveryPerson, String dueDate, String items) {
    String myFormat = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CANADA);
    String date = sdf.format(new Date());
    createInvoicePresenter.createInvoice(authToken, business, deliveryPerson, date, dueDate, items);
  }

  void addItemRow() {
    TableRow row =
        (TableRow) LayoutInflater.from(CreateInvoice.this).inflate(R.layout.item_table_row, null);
    final EditText orderPrice = row.findViewById(R.id.orderPrice);
    orderPrice.setOnFocusChangeListener(
        new View.OnFocusChangeListener() {

          @Override
          public void onFocusChange(View v, boolean hasFocus) {

            if (!hasFocus) {
              try {
                double price = Double.parseDouble(orderPrice.getText().toString());

                DecimalFormat df = new DecimalFormat("#.##");
                currPrice += price;
                totalPrice.setText(String.format("Total Price: %s", df.format(currPrice)));
              } catch (Exception e) {
              }
            }
          }
        });
    tableLayout.addView(row);
  }
}
