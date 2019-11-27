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
    return root;
  }
}
