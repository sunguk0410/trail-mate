package com.example.trail_mate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<List<String>> locationList = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<String>> getLocationList() {
        return locationList;
    }

    public void addLocation(String location) {
        List<String> currentList = locationList.getValue();
        if (currentList != null) {
            currentList.add(location);
            locationList.setValue(currentList); // 리스트 갱신
        }
    }
}
