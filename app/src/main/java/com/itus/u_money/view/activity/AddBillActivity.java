package com.itus.u_money.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.itus.u_money.R;
import com.itus.u_money.contract.AddBillContract;
import com.itus.u_money.databinding.ActivityAddBillBinding;
import com.itus.u_money.databinding.ActivityAddBudgetBinding;
import com.itus.u_money.model.Bill;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.AddBillPresenter;
import com.itus.u_money.view.fragment.ChooseTimeDialogFragment;
import com.itus.u_money.view.fragment.TypeFragment;

import java.util.Objects;

public class AddBillActivity extends AppCompatActivity implements AddBillContract.View {

    ActivityAddBillBinding binding;

    AddBillContract.Presenter presenter;

    Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bill = new Bill();
        presenter = new AddBillPresenter(this);
        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    public void chooseType(View view) {
        Intent intent = new Intent(this, ChooseTypeActivity.class);
        intent.putExtra(ChooseTypeActivity.CHOOSING_TYPE, "Other");
        startActivityForResult(intent, 45);
    }

    @Override
    public void showIcon(int resourceId) {
        binding.icoType.setImageResource(resourceId);
    }

    @Override
    public void showDate(String date) {

    }

    // EVENTS

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45 && resultCode == RESULT_OK) {
            TransactionType transactionType = (TransactionType) data.getSerializableExtra(TypeFragment.TYPE_SELECTED);
            binding.txtType.setText(transactionType.name);
            presenter.getResourceId(transactionType.iconId);
        }
    }

    public void onUpButtonClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_press));
        this.finish();
    }

    public void onSaveButtonClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_press));
        this.finish();
    }

    public void chooseTime(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChooseTimeDialogFragment dialogFragment = ChooseTimeDialogFragment.newInstance("Chọn thời gian");
        dialogFragment.show(fragmentManager, null);
    }
}