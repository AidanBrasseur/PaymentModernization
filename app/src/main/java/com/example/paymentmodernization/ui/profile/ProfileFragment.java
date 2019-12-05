package com.example.paymentmodernization.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.R;

public class ProfileFragment extends Fragment {

  private ProfileViewModel profileViewModel;
  private UserInformation userInformation;
  private TextView fullName;
  private TextView username;
  private TextView bankNum;
  private TextView street;
  private TextView city;
  private TextView region;
  private TextView country;
  private TextView postcode;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
    Intent intent = getActivity().getIntent();
    userInformation = intent.getParcelableExtra("userInformation");
    View root = inflater.inflate(R.layout.fragment_profile, container, false);
    //    final TextView textView = root.findViewById(R.id.text_profile);
    //    profileViewModel
    //        .getText()
    //        .observe(
    //            this,
    //            new Observer<String>() {
    //              @Override
    //              public void onChanged(@Nullable String s) {
    //                textView.setText(s);
    //              }
    //            });
    fullName = root.findViewById(R.id.fullName);
    fullName.setText(userInformation.getFullName());
    username = root.findViewById(R.id.username);
    username.setText(userInformation.getUsername());
    bankNum = root.findViewById(R.id.bankNum);
    bankNum.setText(userInformation.getBankNum());
    street = root.findViewById(R.id.street);
    street.setText(userInformation.getStreetAddress());
    city = root.findViewById(R.id.city);
    city.setText(userInformation.getCity());
    region = root.findViewById(R.id.region);
    region.setText(userInformation.getRegion());
    country = root.findViewById(R.id.country);
    country.setText(userInformation.getCountry());
    postcode = root.findViewById(R.id.postcode);
    postcode.setText(userInformation.getPostalCode());


    return root;
  }


}
