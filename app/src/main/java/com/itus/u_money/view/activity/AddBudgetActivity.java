package com.itus.u_money.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import java.util.Objects;

import com.itus.u_money.R;
import com.itus.u_money.contract.AddBudgetContract;
import com.itus.u_money.databinding.ActivityAddBudgetBinding;
import com.itus.u_money.model.Budget;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.AddBudgetPresenter;
import com.itus.u_money.view.fragment.TypeFragment;

public class AddBudgetActivity extends AppCompatActivity implements AddBudgetContract.View {
    ActivityAddBudgetBinding binding;
    AddBudgetContract.Presenter presenter;
    Budget budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBudgetBinding.inflate(getLayoutInflater());
        budget = new Budget();
        presenter = new AddBudgetPresenter(this);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45 && resultCode == RESULT_OK) {
            TransactionType transactionType = (TransactionType) data.getSerializableExtra(TypeFragment.TYPE_SELECTED);
            budget.transactionTypeId = transactionType.id;
            binding.txtType.setText(transactionType.name);
            presenter.getResourceId(transactionType.iconId);
        }
    }

    @Override
    public void showIcon(int resourceId) {
        binding.icoType.setImageResource(resourceId);
    }
}