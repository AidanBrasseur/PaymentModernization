package com.example.paymentmodernization.ui.legal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.paymentmodernization.R;

public class LegalFragment extends Fragment {

  private LegalViewModel legalViewModel;

  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    legalViewModel = ViewModelProviders.of(this).get(LegalViewModel.class);
    View root = inflater.inflate(R.layout.fragment_legal, container, false);
    final TextView textView = root.findViewById(R.id.text_legal);
    legalViewModel
        .getText()
        .observe(
            this,
            new Observer<String>() {
              @Override
              public void onChanged(@Nullable String s) {
                textView.setText(s);
              }
            });
    return root;
  }
}
