package com.itus.u_money.view.fragment;

import android.app.DatePickerDialog;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @Override
    public void onStart() {
        super.onStart();
        int width = getResources().getDisplayMetrics().widthPixels;
        getDialog().getWindow().setLayout(width * 9 / 10, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChooseTimeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        isLooped = true;
        data = new Bundle();

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
                    data.putString("loop_type", "loop_daily");
                    binding.weekDays.setVisibility(View.GONE);
                    binding.txtIntervalUnit.setText(getResources().getString(R.string.day));
                    return true;
                case R.id.loopWeekly:
                    data.putString("loop_type", "loop_weekly");
                    binding.weekDays.setVisibility(View.VISIBLE);
                    binding.txtIntervalUnit.setText(getResources().getString(R.string.week));
                    return true;
                case R.id.loopMonthly:
                    data.putString("loop_type", "loop_monthly");
                    binding.weekDays.setVisibility(View.GONE);
                    binding.txtIntervalUnit.setText(getResources().getString(R.string.month));
                    return true;
                case R.id.loopYearly:
                    data.putString("loop_type", "loop_yearly");
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
