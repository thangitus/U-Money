package com.itus.u_money.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import java.util.Objects;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityAddBudgetBinding;

public class AddBudgetActivity extends AppCompatActivity {
    ActivityAddBudgetBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBudgetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    public void upHandle(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_press));
        this.finish();
    }

    public void chooseType(View view) {
        Intent intent = new Intent(this, ChooseTypeActivity.class);
        intent.putExtra(ChooseTypeActivity.CHOOSING_TYPE, ChooseTypeActivity.ADD_TRANSACTION);
        startActivityForResult(intent, 45);
    }
}