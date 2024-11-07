package com.example.trail_mate;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.trail_mate.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewBinding 초기화
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 초기 화면 설정 (예: MapsFragment)
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(binding.mainContainer.getId(), new MapsFragment(), String.valueOf(R.id.fragment_maps));
            fragmentTransaction.commit(); // 트랜잭션 커밋
        }

        // BottomNavigationView 리스너 설정
        binding.bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                // 현재 표시 중인 Fragment와 선택된 Fragment가 동일하면 트랜잭션 수행하지 않음
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(binding.mainContainer.getId());
                if (currentFragment != null && currentFragment.getTag() != null && currentFragment.getTag().equals(String.valueOf(itemId))) {
                    return true; // 동일한 Fragment를 다시 호출하지 않음
                }

                // 선택된 Fragment를 표시
                Fragment selectedFragment = null;
                if (itemId == R.id.fragment_maps) {
                    selectedFragment = new MapsFragment();
                } else if (itemId == R.id.fragment_navigation) {
                    selectedFragment = new NavigationFragment();
                } else if (itemId == R.id.fragment_bookmark) {
                    selectedFragment = new BookmarkFragment();
                } else if (itemId == R.id.fragment_profile) {
                    selectedFragment = new ProfileFragment();
                }

                // 트랜잭션을 수행하여 Fragment를 교체
                if (selectedFragment != null) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(binding.mainContainer.getId(), selectedFragment, String.valueOf(itemId)); // Tag를 설정하여 중복 방지
                    fragmentTransaction.addToBackStack(null); // 뒤로 가기 버튼 지원
                    fragmentTransaction.commit(); // 상태 손실을 허용하지 않는 일반적인 commit 사용
                }
                return true;
            }
        });
    }
}
