package com.itus.u_money.view.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.itus.u_money.R;
import com.itus.u_money.databinding.FragmentChooseTimeBinding;
import com.itus.u_money.view.activity.AddBillActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ChooseTimeDialogFragment extends DialogFragment {
    FragmentChooseTimeBinding binding;
    boolean isLooped;
    Bundle data;
    Date startDate;
    Date endDate;


    public ChooseTimeDialogFragment() {}

    public static ChooseTimeDialogFragment newInstance(String title) {
        ChooseTimeDialogFragment fragment = new ChooseTimeDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        binding = FragmentChooseTimeBinding.inflate(LayoutInflater.from(getContext()));
        builder.setView(binding.getRoot());

        builder.setPositiveButton("Xác nhận", this::onConfirm);
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        isLooped = true;
        data = new Bundle();
        data.putString("loop_type", "DAY");
        data.putString("loop_state", "forever");
        data.putSerializable("weekdays", (Serializable) new ArrayList<>());
        data.putInt("loop_number", -1);

        startDate = Calendar.getInstance().getTime();

        binding.swcIsLoop.setOnCheckedChangeListener(this::switchHandle);
        binding.loopTypeGroup.setOnClickListener(this::showLoopTypePopUpMenu);
        binding.startDateGroup.setOnClickListener(this::selectStartDate);
        binding.loopStateGroup.setOnClickListener(this::showLoopStatePopUpMenu);
        binding.endtDateGroup.setOnClickListener(this::selectEndDate);
        binding.monday.setOnCheckedChangeListener(this::weekDaysCheckHandle);
        binding.tuesday.setOnCheckedChangeListener(this::weekDaysCheckHandle);
        binding.wednesday.setOnCheckedChangeListener(this::weekDaysCheckHandle);
        binding.thursday.setOnCheckedChangeListener(this::weekDaysCheckHandle);
        binding.friday.setOnCheckedChangeListener(this::weekDaysCheckHandle);
        binding.saturday.setOnCheckedChangeListener(this::weekDaysCheckHandle);
        binding.sunday.setOnCheckedChangeListener(this::weekDaysCheckHandle);

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout(width * 9 / 10, WindowManager.LayoutParams.WRAP_CONTENT);
    }


    private void onConfirm(DialogInterface dialog, int i) {
        data.putSerializable("start_date", startDate);
        data.putBoolean("is_looped", binding.swcIsLoop.isChecked());

        if (binding.swcIsLoop.isChecked()) {
            int interval = 1;
            if (!binding.edtInterval.getText().toString().equals(""))
                interval = Integer.parseInt(binding.edtInterval.getText().toString());
            data.putInt("interval", interval);

            if (Objects.equals(data.getString("loop_type"), "WEEK")) {
                List<Integer> list = new ArrayList<>();
                if (binding.sunday.isChecked()) list.add(1);
                if (binding.monday.isChecked()) list.add(2);
                if (binding.tuesday.isChecked()) list.add(3);
                if (binding.wednesday.isChecked()) list.add(4);
                if (binding.thursday.isChecked()) list.add(5);
                if (binding.friday.isChecked()) list.add(6);
                if (binding.saturday.isChecked()) list.add(7);

                data.putSerializable("weekdays", (Serializable) list);
            }

            if (Objects.equals(data.getString("loop_state"), "to_date")) {
                data.putSerializable("end_date", endDate);
            }

            if (Objects.equals(data.getString("loop_state"), "specific_number")) {
                data.putInt("loop_number", Integer.parseInt(binding.edtLoopNumber.getText().toString()));
            }
        }

        AddBillActivity activity = (AddBillActivity) requireActivity();
        activity.setTimeData(data);
        dialog.dismiss();
    }

    private void weekDaysCheckHandle(CompoundButton compoundButton, boolean b) {
        if (!isLooped) {
            compoundButton.setChecked(!b);
        }
    }

    private void switchHandle(CompoundButton compoundButton, boolean isChecked) {
        isLooped = isChecked;
        if (!isLooped) {
            binding.txtLoopType.setText("Không lặp lại");
            binding.edtInterval.setEnabled(false);
            binding.edtLoopNumber.setEnabled(false);
        } else {
            binding.edtInterval.setEnabled(true);
            binding.edtLoopNumber.setEnabled(true);
            String loopType = data.getString("loop_type", "loop_daily");
            switch (loopType) {
                case "loop_daily":
                    binding.txtLoopType.setText(getResources().getString(R.string.loop_daily));
                    break;
                case "loop_weekly":
                    binding.txtLoopType.setText(getResources().getString(R.string.loop_weekly));
                    break;
                case "loop_monthly":
                    binding.txtLoopType.setText(getResources().getString(R.string.loop_monthly));
                    break;
                case "loop_yearly":
                    binding.txtLoopType.setText(getResources().getString(R.string.loop_yearly));
                    break;
                default:
                    break;
            }
        }
    }

    private void showLoopStatePopUpMenu(View view) {
        if (!isLooped) return;

        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.popup_menu_loop_state);
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            binding.txtLoopState.setText(menuItem.toString());

            switch (menuItem.getItemId()) {
                case R.id.forever:
                    data.putString("loop_state", "forever");
                    binding.endtDateGroup.setVisibility(View.GONE);
                    binding.loopNumberGroup.setVisibility(View.GONE);
                    return true;
                case R.id.toDate:
                    data.putString("loop_state", "to_date");
                    binding.endtDateGroup.setVisibility(View.VISIBLE);
                    binding.loopNumberGroup.setVisibility(View.GONE);
                    return true;
                case R.id.specificNumber:
                    data.putString("loop_state", "specific_number");
                    binding.endtDateGroup.setVisibility(View.GONE);
                    binding.loopNumberGroup.setVisibility(View.VISIBLE);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }

    public void selectStartDate(View view) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogTheme, (datePicker, i, i1, i2) -> {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            startDate = calendar.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            binding.txtStartDate.setText(sdf.format(calendar.getTime()));
            binding.txtStartDate.invalidate();
            binding.txtStartDate.requestLayout();
        }, year, month, day);
        datePickerDialog.show();
    }

    public void selectEndDate(View view) {
        if (!isLooped) return;

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.DialogTheme, (datePicker, i, i1, i2) -> {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            endDate = calendar.getTime();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
            binding.txtEndDate.setText(sdf.format(calendar.getTime()));
            binding.txtEndDate.invalidate();
            binding.txtEndDate.requestLayout();
        }, year, month, day);
        datePickerDialog.show();
    }

    public void showLoopTypePopUpMenu(View view) {
        if (!isLooped) return;

        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.inflate(R.menu.popup_menu_loop_type);
        popupMenu.setOnMenuItemClickListener(menuItem -> {
            binding.txtLoopType.setText(menuItem.toString());

            switch (menuItem.getItemId()) {
                case R.id.loopDaily:
                    data.putString("loop_type", "DAY");
                    binding.weekDays.setVisibility(View.GONE);
                    binding.txtIntervalUnit.setText(getResources().getString(R.string.day));
                    return true;
                case R.id.loopWeekly:
                    data.putString("loop_type", "WEEK");
                    binding.weekDays.setVisibility(View.VISIBLE);
                    binding.txtIntervalUnit.setText(getResources().getString(R.string.week));
                    return true;
                case R.id.loopMonthly:
                    data.putString("loop_type", "MONTH");
                    binding.weekDays.setVisibility(View.GONE);
                    binding.txtIntervalUnit.setText(getResources().getString(R.string.month));
                    return true;
                case R.id.loopYearly:
                    data.putString("loop_type", "YEAR");
                    binding.weekDays.setVisibility(View.GONE);
                    binding.txtIntervalUnit.setText(getResources().getString(R.string.year));
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
    }
}
