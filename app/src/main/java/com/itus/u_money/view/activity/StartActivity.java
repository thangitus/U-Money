
package com.itus.u_money.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.itus.u_money.contract.StartContract;
import com.itus.u_money.databinding.ActivityStartBinding;
import com.itus.u_money.presenter.StartPresenter;

public class StartActivity extends AppCompatActivity implements StartContract.View {
    ActivityStartBinding binding;
    StartContract.Presenter presenter = new StartPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void onNextClick(View view) {
        String fullName = binding.edtFullName.getText()
                .toString();
        long curAmount = Long.parseLong(binding.currentMoney.getText()
                .toString());

        SharedPreferences pref = getApplicationContext().getSharedPreferences("UMoney", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("IsFirst", false);
        editor.putString("FullName", fullName);
        editor.putLong("CurAmount", curAmount);
        editor.apply();
        presenter.saveBudget(Long.parseLong(binding.edtBudget.getText()
                .toString()));
    }
    @Override
    public void onSaveSuccess() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
