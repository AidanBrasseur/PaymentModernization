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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.paymentmodernization.InvoiceDetails.InvoiceDetailsActivity;
import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.R;

import java.util.ArrayList;

public class InvoicesFragment extends Fragment implements InvoicesView {

  private String authToken;
  private InvoicesPresenter invoicesPresenter;
  private TableLayout invoicesTable;
  private ConstraintLayout layout;
  private Context context;
  private RecyclerView recyclerView;
  private InvoicesAdapter recycleAdapter;
  private RecyclerView.LayoutManager recycleManager;
  private String completedStatus;
  private UserInformation userInformation;
  private SwipeRefreshLayout swipeRefreshLayout;

  public InvoicesFragment(String completedStatus) {
    this.completedStatus = completedStatus;
  }

  @Override
  public View onCreateView(
      @NonNull final LayoutInflater inflater,
      final ViewGroup container,
      final Bundle savedInstanceState) {

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
    this.userInformation = intent.getParcelableExtra("userInformation");
    this.authToken = userInformation.getAuthToken();
    context = getActivity().getApplicationContext();

    // this.invoicesTable = findViewById(R.id.invoices);
    layout = root.findViewById(R.id.coordinatorLayout);
    // displayInvoices(this.authToken);
    recyclerView = root.findViewById(R.id.recyclerView);
    recycleManager = new LinearLayoutManager(context);
    swipeRefreshLayout = root.findViewById(R.id.swipeRefresh);
    swipeRefreshLayout.setRefreshing(false);
    swipeRefreshLayout.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            displayInvoices(authToken);
          }
        });

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
  public void addInvoiceCards(final ArrayList<Invoice> invoices) {
    ArrayList<InvoiceCard> invoiceCards = new ArrayList<>();
    final ArrayList<Invoice> keptInvoice = new ArrayList<>();
    for (Invoice invoice : invoices) {
      String heading = invoice.getSupplier() + " to " + invoice.getBusiness();
      String status = invoice.getStatus();
      String delivery;
      if (invoice.getDueDate() == null) {
        delivery = "Delivery Date TBD";
      } else {
        delivery = "Delivery Date: " + invoice.getDueDate();
      }
      if (this.completedStatus.equals("COMPLETE") && invoice.getStatus().equals("COMPLETE")) {
        invoiceCards.add(new InvoiceCard(heading, delivery, status));
        keptInvoice.add(invoice);
      } else if (!this.completedStatus.equals("COMPLETE")) {
        if (!(invoice.getStatus().equals("COMPLETE"))) {
          invoiceCards.add(new InvoiceCard(heading, delivery, status));
          keptInvoice.add(invoice);
        }
      }
    }
    recycleManager = new LinearLayoutManager(context);
    recycleAdapter = new InvoicesAdapter(invoiceCards);
    recyclerView.setLayoutManager(recycleManager);
    recyclerView.setAdapter(recycleAdapter);
    swipeRefreshLayout.setRefreshing(false);

    recycleAdapter.setOnItemClickListener(
        new InvoicesAdapter.onItemClickListener() {
          @Override
          public void onItemClick(int position) {
            Invoice clickedInvoice = keptInvoice.get(position);
            Intent intent = new Intent(getActivity(), InvoiceDetailsActivity.class);
            intent.putExtra("invoice", clickedInvoice);
            intent.putExtra("authToken", authToken);
            intent.putExtra("userType", userInformation.getUserType());
            getActivity().startActivity(intent);
          }
        });
  }

  @Override
  public void onResume() {
    super.onResume();
    displayInvoices(authToken);
  }
}
