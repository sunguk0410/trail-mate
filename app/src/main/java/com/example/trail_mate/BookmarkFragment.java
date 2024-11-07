package com.example.trail_mate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.trail_mate.databinding.FragmentBookmarkBinding;

public class BookmarkFragment extends Fragment {
    private FragmentBookmarkBinding binding;
    private SharedViewModel sharedViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // ViewBinding 초기화
        binding = FragmentBookmarkBinding.inflate(inflater, container, false);

        // SharedViewModel 초기화
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // 버튼 클릭 시 SharedViewModel에 데이터 추가
        binding.buttonFragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = binding.editTextFromFragment1.getText().toString();
                if (!text.isEmpty()) {
                    sharedViewModel.addLocation(text);
                    binding.editTextFromFragment1.setText(""); // 입력 필드 초기화
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // 메모리 누수 방지
    }
}
