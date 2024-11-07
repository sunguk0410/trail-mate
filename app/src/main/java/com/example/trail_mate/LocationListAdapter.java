package com.example.trail_mate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> {

    private List<String> locationList = new ArrayList<>();
    private OnBookmarkCheckedChangeListener onBookmarkCheckedChangeListener;

    // 인터페이스 정의: 체크박스 상태 변경 시 호출
    public interface OnBookmarkCheckedChangeListener {
        void onBookmarkCheckedChanged(String location, boolean isChecked);
    }

    public void setOnBookmarkCheckedChangeListener(OnBookmarkCheckedChangeListener listener) {
        this.onBookmarkCheckedChangeListener = listener;
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {
        private final TextView locationTextView;
        private final CheckBox bookmarkCheckBox;

        public LocationViewHolder(View itemView, final OnBookmarkCheckedChangeListener listener) {
            super(itemView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            bookmarkCheckBox = itemView.findViewById(R.id.bookmarkCheckBox);

            // 체크박스 클릭 이벤트 처리
            bookmarkCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onBookmarkCheckedChanged(locationTextView.getText().toString(), isChecked);
                }
            });
        }

        public void bind(String location, boolean isChecked) {
            locationTextView.setText(location);
            bookmarkCheckBox.setChecked(isChecked);
        }
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_location, parent, false);
        return new LocationViewHolder(view, onBookmarkCheckedChangeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.bind(locationList.get(position), false);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public void setLocationList(List<String> locations) {
        locationList = locations;
        notifyDataSetChanged();
    }
}
