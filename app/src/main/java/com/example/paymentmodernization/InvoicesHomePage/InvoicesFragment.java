package com.example.paymentmodernization.InvoicesHomePage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
  private Context context;
  private RecyclerView recyclerView;
  private InvoicesAdapter recycleAdapter;
  private RecyclerView.LayoutManager recycleManager;
  private String completedStatus;
  private UserInformation userInformation;
  private View layout;
  private SwipeRefreshLayout swipeRefreshLayout;
  private HomeFragment homeFragment;
  private SearchView searchView;
  private View root;

  public InvoicesFragment(String completedStatus, HomeFragment homeFragment) {
    this.completedStatus = completedStatus;
    this.homeFragment = homeFragment;
  }

  @Override
  public View onCreateView(
      @NonNull final LayoutInflater inflater,
      final ViewGroup container,
      final Bundle savedInstanceState) {

    root = inflater.inflate(R.layout.fragment_invoices, container, false);

    return root;
  }



  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    setHasOptionsMenu(true);

    Intent intent = getActivity().getIntent();
    this.userInformation = intent.getParcelableExtra("userInformation");
    this.authToken = userInformation.getAuthToken();
    context = getActivity().getApplicationContext();

    // this.invoicesTable = findViewById(R.id.invoices);
    layout = root.findViewById(R.id.coordinatorLayout);
    // displayInvoices(this.authToken);
    recyclerView = root.findViewById(R.id.recyclerView);
    recycleManager = new LinearLayoutManager(context);
    this.swipeRefreshLayout = root.findViewById(R.id.swipe);
    swipeRefreshLayout.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            homeFragment.getInvoices();
            swipeRefreshLayout.setRefreshing(false);
          }
        });
    super.onViewCreated(view, savedInstanceState);
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
        delivery = "Due Date: " + invoice.getDueDate();
      }
      if (this.completedStatus.equals("COMPLETE") && invoice.getStatus().equals("COMPLETE")) {
        invoiceCards.add(
            new InvoiceCard(
                invoice, heading, delivery, status, invoice.getItems(), invoice.getInvoiceId()));
        keptInvoice.add(invoice);
      } else if (!this.completedStatus.equals("COMPLETE")) {
        if (!(invoice.getStatus().equals("COMPLETE"))) {
          invoiceCards.add(
              new InvoiceCard(
                  invoice, heading, delivery, status, invoice.getItems(), invoice.getInvoiceId()));
          keptInvoice.add(invoice);
        }
      }
    }
    recycleManager = new LinearLayoutManager(context);
    recycleAdapter = new InvoicesAdapter(invoiceCards);
    recyclerView.setLayoutManager(recycleManager);
    recyclerView.setAdapter(recycleAdapter);

    recycleAdapter.setOnItemClickListener(
        new InvoicesAdapter.onItemClickListener() {
          @Override
          public void onItemClick(int position) {
            InvoiceCard clickedcard = recycleAdapter.getInvoiceCards().get(position);
            Invoice clickedInvoice = clickedcard.getInvoice();
            Intent intent = new Intent(getActivity(), InvoiceDetailsActivity.class);
            intent.putExtra("invoice", clickedInvoice);
            intent.putExtra("authToken", authToken);
            intent.putExtra("userType", userInformation.getUserType());
            getActivity().startActivity(intent);
          }
        });
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    // setHasOptionsMenu(true);

    MenuInflater inflater2 = inflater;
    inflater2.inflate(R.menu.nav_drawer, menu);

    MenuItem searchItem = menu.findItem(R.id.action_search);
    searchView = (SearchView) searchItem.getActionView();
    searchView.setIconifiedByDefault(false);

    searchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String s) {
            return false;
          }

          @Override
          public boolean onQueryTextChange(String s) {
            recycleAdapter.getFilter().filter(s);
            return false;
          }
        });

    super.onCreateOptionsMenu(menu, inflater);
  }
}
