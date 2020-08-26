package com.itus.u_money.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import com.itus.u_money.R;
import com.itus.u_money.contract.AddBudgetContract;
import com.itus.u_money.databinding.ActivityAddBudgetBinding;
import com.itus.u_money.model.Budget;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.AddBudgetPresenter;
import com.itus.u_money.view.fragment.TypeFragment;
import com.itus.u_money.view.utils.DateTime;

public class AddBudgetActivity extends AppCompatActivity implements AddBudgetContract.View, PopupMenu.OnMenuItemClickListener {
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
        intent.putExtra(ChooseTypeActivity.CHOOSING_TYPE, "Other");
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

    public void showTimeMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu_time);

        popupMenu.getMenu().getItem(0).setTitle("Tuần này (" + DateTime.getStringFirstDayOfThisWeek() + " - " + DateTime.getStringLastDayOfThisWeek() + ")");
        popupMenu.getMenu().getItem(1).setTitle("Tháng này (" + DateTime.getStringFirstDayOfThisMonth() + " - " + DateTime.getStringLastDayOfThisMonth() + ")");
        popupMenu.getMenu().getItem(2).setTitle("Năm này (" + DateTime.getStringFirstDayOfThisYear() + " - " + DateTime.getStringLastDayOfThisYear() + ")");
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        binding.txtTime.setText(menuItem.toString());

        switch (menuItem.getItemId()) {
            case R.id.week:
                budget.startTime = DateTime.getFirstDayOfThisWeek();
                budget.loopType = "WEEK";
                return true;
            case  R.id.month:
                budget.startTime = DateTime.getFirstDayOfThisMonth();
                budget.loopType = "MONTH";
                return true;
            case R.id.year:
                budget.startTime = DateTime.getFirstDayOfThisYear();
                budget.loopType = "YEAR";
                return true;
            default:
                return false;
        }
    }

    public void onSaveClick(View view) {
        String amount = binding.edtAmount.getText().toString();
        if (!amount.equals(""))
            budget.amount = Long.parseLong(amount);
        else
            budget.amount = 0;

        budget.usedAmount = 0;
        // Update used amount

        budget.isRepeated = binding.checkbox.isChecked();

        presenter.saveBudget(budget);

        Toast.makeText(this, "Thêm ngân sách thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
}