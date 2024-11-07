package com.example.trail_mate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private final MutableLiveData<List<String>> locationList = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<String>> bookmarkedList = new MutableLiveData<>(new ArrayList<>()); // 찜한 리스트

    public LiveData<List<String>> getLocationList() {
        return locationList;
    }

    public LiveData<List<String>> getBookmarkedList() {
        return bookmarkedList;
    }

    public void addLocation(String location) {
        List<String> currentList = locationList.getValue();
        if (currentList != null) {
            currentList.add(location);
            locationList.setValue(currentList);
        }
    }

    // 찜 리스트에 항목 추가
    public void addBookmark(String location) {
        List<String> currentBookmarks = bookmarkedList.getValue();
        if (currentBookmarks != null && !currentBookmarks.contains(location)) {
            currentBookmarks.add(location);
            bookmarkedList.setValue(currentBookmarks); // 갱신
        }
    }
}
