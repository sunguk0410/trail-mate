package com.example.trail_mate;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private BottomSheetBehavior<View> bottomSheetBehavior;
    private GoogleMap mMap;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // SupportMapFragment 초기화 및 getMapAsync로 콜백 설정
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // BottomSheet 초기화
        View bottomSheet = view.findViewById(R.id.bottomSheet);
        if (bottomSheet != null) {
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

            // 드래그 활성화
            bottomSheetBehavior.setDraggable(true);

            // 초기 상태 설정
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            // BottomSheet 상태 변경 리스너 추가
            bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        Log.d("BottomSheet", "BottomSheet expanded");
                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        Log.d("BottomSheet", "BottomSheet collapsed");
                    } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        Log.d("BottomSheet", "BottomSheet dragging");
                    } else if (newState == BottomSheetBehavior.STATE_SETTLING) {
                        Log.d("BottomSheet", "BottomSheet settling");
                    } else if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        Log.d("BottomSheet", "BottomSheet hidden");
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // 드래그 또는 슬라이드 중일 때 호출됩니다. slideOffset은 0.0 (축소)에서 1.0 (완전히 확장) 사이입니다.
                    Log.d("BottomSheet", "BottomSheet sliding: " + slideOffset);
                }
            });
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 지도에 서울 위치를 추가
        LatLng SEOUL = new LatLng(37.5665, 126.9780);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");

        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));
    }
}
