package com.example.paymentmodernization.CreateInvoice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.MainActivity.NavDrawer;
import com.example.paymentmodernization.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateInvoice extends AppCompatActivity implements CreateInvoiceView {
  private EditText businessText;
  private EditText deliveryPersonText;
  private EditText dueDateText;
  private EditText orderDesc;
  private EditText orderQt;
  private EditText price;
  private Button submitButton;
  private ProgressBar progressBar;
  private UserInformation userInformation;
  private CreateInvoicePresenter createInvoicePresenter;
  private Calendar myCalendar;

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
    orderDesc = findViewById(R.id.orderDesc);
    orderQt = findViewById(R.id.orderQt);
    price = findViewById(R.id.orderPrice);
    progressBar = findViewById(R.id.progress);
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

    dueDateText.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            // TODO Auto-generated method stub
            new DatePickerDialog(
                    CreateInvoice.this,
                    date,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
          }
        });
    submitButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            try {
              JSONArray items = new JSONArray();
              JSONObject tempItem = new JSONObject();
              tempItem.put("description", orderDesc.getText().toString());
              tempItem.put("quantity", orderQt.getText().toString());
              tempItem.put("price", price.getText().toString());
              items.put(tempItem);
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
}
