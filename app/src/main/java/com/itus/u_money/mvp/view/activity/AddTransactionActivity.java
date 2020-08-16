package com.itus.u_money.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityAddTransactionBinding;

import java.util.Objects;

public class AddTransactionActivity extends AppCompatActivity {
   ActivityAddTransactionBinding binding;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
      initActionBar();
   }

   private void initActionBar() {
      setSupportActionBar(binding.toolbar);
      Objects.requireNonNull(getSupportActionBar())
             .setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
   }
   public void saveTransaction(View view) {

   }
}