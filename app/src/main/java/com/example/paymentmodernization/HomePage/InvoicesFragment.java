package com.example.paymentmodernization.HomePage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paymentmodernization.R;

import java.util.ArrayList;

public class InvoicesFragment extends Fragment implements InvoicesView {

  private String authToken;
  private InvoicesPresenter invoicesPresenter;
  private TableLayout invoicesTable;
  private ConstraintLayout layout;
  private Context context;
  private RecyclerView recyclerView;
  private RecyclerView.Adapter recycleAdapter;
  private RecyclerView.LayoutManager recycleManager;

  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.fragment_invoices, container, false);
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
    Intent intent = getActivity().getIntent();

    this.authToken = intent.getStringExtra("authToken");
    context = getActivity().getApplicationContext();

    // this.invoicesTable = findViewById(R.id.invoices);
    layout = root.findViewById(R.id.coordinatorLayout);
    displayInvoices(this.authToken);
    recyclerView = root.findViewById(R.id.recyclerView);
    recycleManager = new LinearLayoutManager(context);
    //    LinearLayoutManager manager = new LinearLayoutManager(this);
    //    manager.setOrientation(LinearLayoutManager.VERTICAL);
    //    recyclerView.setLayoutManager(manager);
    return root;
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
    for (Invoice invoice : invoices) {
      String heading = invoice.getSupplier() + " to " + invoice.getBusiness();
      String delivery;
      if (invoice.getDueDate() == null) {
        delivery = "Delivery Date TBD";
      } else {
        delivery = "Delivery Date: " + invoice.getDueDate();
      }
      invoiceCards.add(new InvoiceCard(heading, delivery));
    }

    recycleAdapter = new InvoicesAdapter(invoiceCards);
    recyclerView.setLayoutManager(recycleManager);
    recyclerView.setAdapter(recycleAdapter);

    System.out.println("************Call addinvoices table *******");
  }
}
