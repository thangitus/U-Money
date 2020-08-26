package com.itus.u_money.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.itus.u_money.R;
import com.itus.u_money.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {
   FragmentUserBinding binding;

   public UserFragment() {
   }
   @Nullable
   @Override
   public View onCreateView(
           @NonNull LayoutInflater inflater,
           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = FragmentUserBinding.inflate(getLayoutInflater());
      return binding.getRoot();
   }
   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      SharedPreferences pref = getContext().getSharedPreferences("UMoney", 0); // 0 - for private mode
      String fullName = pref.getString("FullName", "");
      long curAmount = pref.getLong("CurAmount", 0);
      binding.edtFullName.setText(fullName);
      binding.currentMoney.setText(curAmount + " VND");
      binding.edtBudget.setText(" ");
   }
}
