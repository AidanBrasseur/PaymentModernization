package com.example.paymentmodernization.HomePage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.paymentmodernization.R;
import com.example.paymentmodernization.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity implements  HomePageView{

    private String authToken;
    private InvoicesPresenter invoicesPresenter;
    private TableLayout invoicesTable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home_page);
    /*SectionsPagerAdapter sectionsPagerAdapter =
        new SectionsPagerAdapter(this, getSupportFragmentManager());
    ViewPager viewPager = findViewById(R.id.view_pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
    FloatingActionButton fab = findViewById(R.id.fab);

    fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
          }
        });*/
      /**
       * getting invoices
       */
    invoicesPresenter = new InvoicesPresenter(this, new InvoicesInteractor());
    Intent intent = getIntent();

    this.authToken = intent.getStringExtra("authToken");
    displayInvoices(this.authToken);


    this.invoicesTable = findViewById(R.id.invoices);

  }

    /**
     * tell presenter to get invoices
     * @param authToken
     */
  public void displayInvoices(String authToken){

      invoicesPresenter.invoices(authToken);

  }

  @Override
    public void addInvoicesToTable(ArrayList<LinkedTreeMap>invoices ){
      //adding table header
      TableRow tr_head = new TableRow(this);
      tr_head.setId((10));
      tr_head.setBackgroundColor(Color.GRAY);
      tr_head.setLayoutParams(new TableRow.LayoutParams(
              TableRow.LayoutParams.FILL_PARENT,
              TableRow.LayoutParams.WRAP_CONTENT));

      TextView label_supplier = new TextView(this);
      label_supplier.setId((20));
      label_supplier.setText("supplier");
      label_supplier.setTextColor(Color.WHITE);
      label_supplier.setPadding(5, 5, 5, 5);
      tr_head.addView(label_supplier);

      TextView label_business = new TextView(this);
      label_business.setId((20));
      label_business.setText("Bussiness");
      label_business.setTextColor(Color.WHITE);
      label_business.setPadding(5, 5, 5, 5);
      tr_head.addView(label_business);

      invoicesTable.addView(tr_head, new TableLayout.LayoutParams(
              TableLayout.LayoutParams.FILL_PARENT,
              TableLayout.LayoutParams.WRAP_CONTENT
      ));





  }


}
