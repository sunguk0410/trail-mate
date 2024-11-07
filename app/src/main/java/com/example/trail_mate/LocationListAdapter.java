package com.example.trail_mate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    // 위치 데이터를 저장하는 리스트
    private List<String> locationList = new ArrayList<>();

    // ViewHolder 정의
    static class LocationViewHolder extends RecyclerView.ViewHolder {
        private final TextView locationTextView;

        public LocationViewHolder(View itemView) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
        }

        public void bind(String location) {
            locationTextView.setText(location);
        }
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, parent, false);
        return new LocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.bind(locationList.get(position));
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    // 위치 데이터를 설정하는 메서드
    public void setLocationList(List<String> locations) {
        locationList = locations;
        notifyDataSetChanged();
    }

    // 새로운 위치를 추가하는 메서드
    public void addLocation(String location) {
        locationList.add(location);
        notifyItemInserted(locationList.size() - 1);
    }
}
