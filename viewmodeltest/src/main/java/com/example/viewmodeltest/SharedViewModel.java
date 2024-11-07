package com.example.viewmodeltest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<CharSequence> inputText = new MutableLiveData<>();

    public LiveData<CharSequence> getData() {
        return inputText;
    }

    public void updateText(CharSequence input) {
        inputText.setValue(input);
    }
}
