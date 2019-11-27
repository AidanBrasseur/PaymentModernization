package com.example.paymentmodernization.ui.legal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LegalViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public LegalViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is a legal fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}
