package com.example.trail_mate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.trail_mate.databinding.FragmentBookmarkBinding;
import java.util.List;

public class BookmarkFragment extends Fragment {

    private FragmentBookmarkBinding binding;
    private SharedViewModel sharedViewModel;
    private LocationListAdapter locationListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ViewModel 초기화 및 RecyclerView 설정
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        RecyclerView recyclerView = binding.recyclerView;
        locationListAdapter = new LocationListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(locationListAdapter);

        // ViewModel의 bookmarkedList 관찰하여 찜 리스트 업데이트
        sharedViewModel.getBookmarkedList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> bookmarks) {
                locationListAdapter.setLocationList(bookmarks);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
