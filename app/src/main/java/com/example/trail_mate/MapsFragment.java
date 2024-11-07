package com.example.trail_mate;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trail_mate.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapsBinding binding;
    private GoogleMap mMap;
    private SharedViewModel sharedViewModel;
    private LocationListAdapter locationListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMapsBinding.inflate(inflater, container, false);
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

        // 체크박스 상태 변경 리스너 설정: 찜 리스트 업데이트
        locationListAdapter.setOnBookmarkCheckedChangeListener(new LocationListAdapter.OnBookmarkCheckedChangeListener() {
            @Override
            public void onBookmarkCheckedChanged(String location, boolean isChecked) {
                if (isChecked) {
                    sharedViewModel.addBookmark(location); // 찜 리스트에 추가
                }
            }
        });

        // ViewModel의 locationList 관찰하여 데이터 업데이트
        sharedViewModel.getLocationList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> locations) {
                locationListAdapter.setLocationList(locations);
            }
        });

        // 지도 초기화
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 서울 위치에 마커 추가 및 카메라 이동
        LatLng SEOUL = new LatLng(37.5665, 126.9780);
        MarkerOptions markerOptions = new MarkerOptions().position(SEOUL).title("서울");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // 메모리 누수 방지
    }
}
