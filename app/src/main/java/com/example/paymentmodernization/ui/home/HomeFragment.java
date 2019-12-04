package com.example.paymentmodernization.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.paymentmodernization.CreateInvoice.CreateInvoice;
import com.example.paymentmodernization.HomePage.InvoicesFragment;
import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.R;
import com.example.paymentmodernization.ui.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

// import com.example.paymentmodernization.ui.slideshow.SlideshowFragment;

public class HomeFragment extends Fragment {

  private HomeViewModel homeViewModel;
  private FloatingActionButton fab;
  private UserInformation userInformation;
  private InvoicesPresenter invoicesPresenter;
  private InvoicesFragment completeInvoiceFragment;
  private InvoicesFragment incompleteInvoiceFragment;
  private ArrayList<Invoice> invoices;
  private SectionsPagerAdapter sectionsPagerAdapter;
  private ViewPager viewPager;
  TabLayout tabs;
  View root;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
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



    //    final TextView textView = root.findViewById(R.id.text_home);
    //    homeViewModel
    //        .getText()
    //        .observe(
    //            this,
    //            new Observer<String>() {
    //              @Override
    //              public void onChanged(@Nullable String s) {
    //                textView.setText(s);
    //              }
    //            });
    sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
    // Temporary until invoice tabs go in
    System.out.println("new invoices fragment created *******************************");

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

  @Override
  public void onResume() {
    System.out.println("CALLED ON RESUMEEEE ____________________________-_");
    getInvoices();
    super.onResume();
  }

  public void getInvoices(){
    System.out.println(" geting ----------------------------------");
    invoicesPresenter.invoices(userInformation.getAuthToken());


  }

  public void updateInvoices(ArrayList<Invoice> newInvoices){


    incompleteInvoiceFragment.addInvoiceCards(newInvoices);
    completeInvoiceFragment.addInvoiceCards(newInvoices);
    this.invoices = newInvoices;

  }

  @Override
  public void onPause() {
    super.onPause();
  }
}
