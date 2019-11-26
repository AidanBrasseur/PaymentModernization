package com.example.paymentmodernization.HomePage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.view.ViewGroup;
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

public class HomePageActivity extends AppCompatActivity implements HomePageView {

  private String authToken;
  private InvoicesPresenter invoicesPresenter;
  private TableLayout invoicesTable;
  private ConstraintLayout layout;
  private Context context;
  private RecyclerView recyclerView;
  private RecyclerView.Adapter recycleAdapter;
  private RecyclerView.LayoutManager recycleManager;

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
    /** getting invoices */
    invoicesPresenter = new InvoicesPresenter(this, new InvoicesInteractor());
    Intent intent = getIntent();

    this.authToken = intent.getStringExtra("authToken");
    context = getApplicationContext();

    //this.invoicesTable = findViewById(R.id.invoices);
    layout = findViewById(R.id.coordinatorLayout);
    displayInvoices(this.authToken);
    recyclerView = findViewById(R.id.recyclerView);
    recycleManager = new LinearLayoutManager(this);
//    LinearLayoutManager manager = new LinearLayoutManager(this);
//    manager.setOrientation(LinearLayoutManager.VERTICAL);
//    recyclerView.setLayoutManager(manager);
  }

  /**
   * tell presenter to get invoices
   *
   * @param authToken
   */
  public void displayInvoices(String authToken) {

    invoicesPresenter.invoices(authToken);
  }

  @Override
  public void addInvoiceCards(ArrayList<Invoice> invoices) {
    ArrayList<InvoiceCard> invoiceCards = new ArrayList<>();
    for (Invoice invoice: invoices){
      String heading = invoice.getSupplier() + " to " + invoice.getBusiness();
      String delivery;
      if (invoice.getDueDate() == null) {
        delivery = "Delivery Date TBD";
      }
      else {delivery = "Delivery Date: " + invoice.getDueDate();}
      invoiceCards.add(new InvoiceCard(heading, delivery));
    }

    recycleAdapter = new InvoicesAdapter(invoiceCards);
    recyclerView.setLayoutManager(recycleManager);
    recyclerView.setAdapter(recycleAdapter);




    System.out.println("************Call addinvoices table *******");
  }
}
