package com.example.paymentmodernization.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.paymentmodernization.HomePage.InvoicesFragment;
import com.example.paymentmodernization.Login.LoginActivity;
import com.example.paymentmodernization.R;
import com.example.paymentmodernization.ui.SectionsPagerAdapter;
//import com.example.paymentmodernization.ui.slideshow.SlideshowFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {

  private HomeViewModel homeViewModel;
  private FloatingActionButton fab;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    View root = inflater.inflate(R.layout.fragment_home, container, false);
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
    sectionsPagerAdapter.addFragment(new InvoicesFragment("NOT_COMPLETE"), "Invoices");
    sectionsPagerAdapter.addFragment(new InvoicesFragment("COMPLETE"), "Completed Invoices");
    ViewPager viewPager = root.findViewById(R.id.view_pager);
    viewPager.setAdapter(sectionsPagerAdapter);
    TabLayout tabs = root.findViewById(R.id.tabs);
    tabs.setupWithViewPager(viewPager);
    FloatingActionButton fab = root.findViewById(R.id.fab);
    fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
          }
        });
    return root;
  }
}
