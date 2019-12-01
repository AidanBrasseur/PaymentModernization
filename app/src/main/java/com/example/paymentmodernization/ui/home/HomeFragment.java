package com.example.paymentmodernization.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
    Intent intent = getActivity().getIntent();
    userInformation = intent.getParcelableExtra("userInformation");
    String userType = userInformation.getUserType();
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
    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
    // Temporary until invoice tabs go in
    System.out.println("new invoices fragment created *******************************");

    invoicesPresenter = new InvoicesPresenter(this, new InvoicesInteractor());
    completeInvoiceFragment = new InvoicesFragment("COMPLETE");
    incompleteInvoiceFragment = new InvoicesFragment("NOT_COMPLETE");


    sectionsPagerAdapter.addFragment(incompleteInvoiceFragment, "Invoices");
    sectionsPagerAdapter.addFragment(completeInvoiceFragment, "Completed Invoices");
    ViewPager viewPager = root.findViewById(R.id.view_pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = root.findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
    FloatingActionButton fab = root.findViewById(R.id.fab);
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
    getInvoices();
    return root;
  }


  public void getInvoices(){
    invoicesPresenter.invoices(userInformation.getAuthToken());

  }

  public void updateInvoices(ArrayList<Invoice> invoices){

    incompleteInvoiceFragment.addInvoiceCards(invoices);
    completeInvoiceFragment.addInvoiceCards(invoices);

  }


}
