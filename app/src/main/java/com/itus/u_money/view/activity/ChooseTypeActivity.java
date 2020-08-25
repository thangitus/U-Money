package com.itus.u_money.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import android.content.Intent;
import android.os.Bundle;

import com.itus.u_money.databinding.ActivityScreenSlideBinding;
import com.itus.u_money.view.fragment.TypeFragment;

import java.util.Objects;

public class ChooseTypeActivity extends AppCompatActivity {

   public static final String CHOOSING_TYPE = "CHOOSING_TYPE";
   public static final String ADD_TRANSACTION = "ADD_TRANSACTION";

   private ActivityScreenSlideBinding binding;
   private String currentChoosingType;
   private int tab_number = 0;
   Intent intent;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityScreenSlideBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());

      initActionBar();

      intent = getIntent();
      currentChoosingType = intent.getStringExtra(CHOOSING_TYPE);

      assert currentChoosingType != null;
      if (currentChoosingType.equalsIgnoreCase(ADD_TRANSACTION)) {
         tab_number = 2;
      } else {
         tab_number = 1;
      }

      SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
      binding.viewPager.setAdapter(pagerAdapter);

      binding.tabs.setupWithViewPager(binding.viewPager);
      binding.toolbar.setTitle("Chọn nhóm");
   }

   private void initActionBar() {
      setSupportActionBar(binding.toolbar);
      Objects.requireNonNull(getSupportActionBar())
             .setDisplayHomeAsUpEnabled(true);
      binding.toolbar.setNavigationOnClickListener(view -> finish());
   }

   public class SectionPagerAdapter extends FragmentPagerAdapter {

      public SectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
         super(fm, behavior);
      }

      @NonNull
      @Override
      public Fragment getItem(int position) {
         return TypeFragment.newInstance(position, currentChoosingType, intent);
      }

      @Override
      public int getCount() {
         return tab_number;
      }

      @Nullable
      @Override
      public CharSequence getPageTitle(int position) {
         switch (position) {
            case 0:
               return "Khoản thu";
            case 1:
               return "Khoản chi";
            default:
               return null;
         }
      }
   }
}