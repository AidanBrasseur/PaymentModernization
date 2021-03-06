package com.example.paymentmodernization.InvoicesHomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.paymentmodernization.CreateInvoice.CreateInvoice;
import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.R;
import com.example.paymentmodernization.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

  private TabLayout tabs;
  private View root;
  private FloatingActionButton fab;
  private UserInformation userInformation;
  private InvoicesPresenter invoicesPresenter;
  private InvoicesFragment completeInvoiceFragment;
  private InvoicesFragment incompleteInvoiceFragment;
  private ArrayList<Invoice> invoices;
  private SectionsPagerAdapter sectionsPagerAdapter;
  private ViewPager viewPager;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    root = inflater.inflate(R.layout.fragment_home, container, false);

    return root;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    Intent intent = getActivity().getIntent();
    userInformation = intent.getParcelableExtra("userInformation");
    String userType = userInformation.getUserType();
    this.invoices = new ArrayList<>();

    sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
    invoicesPresenter = new InvoicesPresenter(this, new InvoicesInteractor());
    completeInvoiceFragment = new InvoicesFragment("COMPLETE", this);
    incompleteInvoiceFragment = new InvoicesFragment("NOT_COMPLETE", this);

    sectionsPagerAdapter.addFragment(incompleteInvoiceFragment, "Invoices");
    sectionsPagerAdapter.addFragment(completeInvoiceFragment, "Completed Invoices");
    viewPager = root.findViewById(R.id.view_pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    tabs = root.findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
    fab = root.findViewById(R.id.fab);
    if (!userType.equals("SUPPLIER")) {
      fab.hide();
    }
    fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(getActivity(), CreateInvoice.class);
            intent.putExtra("userInformation", userInformation);
            getActivity().startActivity(intent);
          }
        });
  }

  /**
   * once we get back to the homepage, we retreive incoices again to refresh.
   */
  @Override
  public void onResume() {
    super.onResume();
    getInvoices();
  }

  public void getInvoices() {
    invoicesPresenter.invoices(userInformation.getAuthToken());
  }


  /**
   * add new invoices to current fragments
   * @param newInvoices
   */
  public void updateInvoices(ArrayList<Invoice> newInvoices) {

    incompleteInvoiceFragment.addInvoiceCards(newInvoices);
    completeInvoiceFragment.addInvoiceCards(newInvoices);
    this.invoices = newInvoices;
  }

}
