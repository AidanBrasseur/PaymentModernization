package com.example.paymentmodernization.CreateInvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.MainActivity.NavDrawer;
import com.example.paymentmodernization.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateInvoice extends AppCompatActivity implements CreateInvoiceView {
  private EditText businessText;
  private EditText deliveryPersonText;
  private EditText dueDateText;
  private EditText orderDesc;
  private EditText orderQt;
  private EditText price;
  private FloatingActionButton fab;
  private ProgressBar progressBar;
  private UserInformation userInformation;
  private CreateInvoicePresenter createInvoicePresenter;

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
    dueDateText.setText("2019-11-17 18:45:00");
    orderDesc = findViewById(R.id.orderDesc);
    orderQt = findViewById(R.id.orderQt);
    price = findViewById(R.id.orderPrice);
    progressBar = findViewById(R.id.progress);
    createInvoicePresenter = new CreateInvoicePresenter(this, new CreateInvoiceInteractor());
    fab = findViewById(R.id.fab);
    fab.setOnClickListener(
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
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = formatter.format(new Date());
    createInvoicePresenter.createInvoice(authToken, business, deliveryPerson, date, dueDate, items);
  }
}
