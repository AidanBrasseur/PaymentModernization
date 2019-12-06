package com.example.paymentmodernization.Settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class SettingsViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public SettingsViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is a settings fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}
