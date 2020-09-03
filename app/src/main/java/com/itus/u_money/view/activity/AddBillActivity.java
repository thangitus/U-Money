package com.itus.u_money.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.itus.u_money.R;
import com.itus.u_money.contract.AddBillContract;
import com.itus.u_money.databinding.ActivityAddBillBinding;
import com.itus.u_money.databinding.ActivityAddBudgetBinding;
import com.itus.u_money.model.Bill;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.AddBillPresenter;
import com.itus.u_money.view.fragment.ChooseTimeDialogFragment;
import com.itus.u_money.view.fragment.TypeFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    public void setTimeData(Bundle data) {
        bill.weekDaysList = new ArrayList<>();
        bill.endDate = null;

        bill.startDate = (Date) data.getSerializable("start_date");
        boolean isLooped = data.getBoolean("is_looped", false);

        if (isLooped) {
            bill.loopType = data.getString("loop_type");
            bill.interval = data.getInt("interval", 1);

            if (bill.loopType.equals("WEEK")) {
                bill.weekDaysList = (List<Integer>) data.getSerializable("weekdays");
            }

            String loopState = data.getString("loop_state", "forever");

            if (loopState.equals("to_date")) {
                bill.loopNumber = -1;
                bill.endDate = (Date) data.getSerializable("end_date");
            } else if (loopState.equals("specific_number")) {
                bill.loopNumber = data.getInt("loop_number", 1);
            } else {
                bill.loopNumber = -1;
                bill.endDate = null;
            }
        } else {
            bill.loopNumber = 0;
        }
    }

    // EVENTS

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 45 && resultCode == RESULT_OK) {
            TransactionType transactionType = (TransactionType) data.getSerializableExtra(TypeFragment.TYPE_SELECTED);
            binding.txtType.setText(transactionType.name);
            bill.typeId = transactionType.id;
            presenter.getResourceId(transactionType.iconId);
        }
    }

    public void onUpButtonClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_press));
        this.finish();
    }

    public void onSaveButtonClick(View view) {
        bill.amount = 0;
        if (!binding.edtAmount.getText().toString().equals(""))
        bill.amount = Integer.parseInt(binding.edtAmount.getText().toString());
        bill.note = binding.edtNote.getText().toString();

        try {
            presenter.saveBill(bill);
            Toast.makeText(this, "Thêm hóa đơn thành công", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Thêm hóa đơn thất bại", Toast.LENGTH_SHORT).show();
        }

        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_press));
        this.finish();
    }

    public void chooseTime(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ChooseTimeDialogFragment dialogFragment = ChooseTimeDialogFragment.newInstance("Chọn thời gian");
        dialogFragment.show(fragmentManager, null);
    }
}