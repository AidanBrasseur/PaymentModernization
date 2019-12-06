package com.example.paymentmodernization.Security;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class SecurityViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public SecurityViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is security fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}
