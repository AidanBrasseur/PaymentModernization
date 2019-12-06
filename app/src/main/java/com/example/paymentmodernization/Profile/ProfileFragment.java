package com.example.paymentmodernization.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.paymentmodernization.Login.UserInformation;
import com.example.paymentmodernization.R;

/** Displays the profile information for the current user */
public class ProfileFragment extends Fragment {

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
    Intent intent = getActivity().getIntent();
    userInformation = intent.getParcelableExtra("userInformation");
    View root = inflater.inflate(R.layout.fragment_profile, container, false);
    fullName = root.findViewById(R.id.fullName);
    fullName.setText(userInformation.getFullName());
    username = root.findViewById(R.id.username);
    username.setText(userInformation.getUsername());
    if (!userInformation.getUserType().equals("DELIVERY_PERSON")) {
      root.findViewById(R.id.bankNumText).setVisibility(View.VISIBLE);
      root.findViewById(R.id.streetText).setVisibility(View.VISIBLE);
      root.findViewById(R.id.cityText).setVisibility(View.VISIBLE);
      root.findViewById(R.id.regionText).setVisibility(View.VISIBLE);
      root.findViewById(R.id.countryText).setVisibility(View.VISIBLE);
      root.findViewById(R.id.postalCodeText).setVisibility(View.VISIBLE);
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
      postcode = root.findViewById(R.id.postalCode);
      postcode.setText(userInformation.getPostalCode());
    }
    return root;
  }
}
