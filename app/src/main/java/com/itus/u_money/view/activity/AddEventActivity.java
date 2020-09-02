package com.itus.u_money.view.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.itus.u_money.R;
import com.itus.u_money.contract.AddEventContract;
import com.itus.u_money.databinding.ActivityAddEventBinding;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Event;
import com.itus.u_money.model.Icon;
import com.itus.u_money.presenter.AddEventPresenter;
import com.itus.u_money.view.fragment.ChooseIconDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class AddEventActivity extends AppCompatActivity {

   private ActivityAddEventBinding binding;

   private Event event;

   AddEventContract.Presenter presenter;

   public Icon icon;
   Calendar myCalendar;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityAddEventBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
      myCalendar = Calendar.getInstance();
      presenter = new AddEventPresenter();
      event = new Event();
      initActionBar();
   }

   private void initActionBar() {
      setSupportActionBar(binding.toolbar);
      Objects.requireNonNull(getSupportActionBar())
             .setDisplayHomeAsUpEnabled(false);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
      getSupportActionBar().setDisplayShowHomeEnabled(false);
   }

   public void pickIcon(View view) {
      DialogFragment dialogFragment = ChooseIconDialogFragment.Companion.newInstance();
      dialogFragment.show(getSupportFragmentManager(), "dialog");
   }
   public void updateIcon(Icon icon) {
      this.icon = icon;
      binding.icon.setImageResource(icon.resourceId);
   }

   private void showDateSelected(Calendar myCalendar) {
      String myFormat = "dd/MM/yyyy";
      SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
      binding.edtDate.setText(sdf.format(myCalendar.getTime()));
      binding.edtDate.invalidate();
      binding.edtDate.requestLayout();
   }

   // EVENTS

   public void onUpButtonClick(View view) {
      view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_press));
      this.finish();
   }

   public void onSaveButtonClick(View view) {
      view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.image_press));

      if (icon == null) {
         event.iconId = 1;
      } else {
         event.iconId = icon.id;
      }
      event.endTime = myCalendar.getTime();
      event.name = binding.edtName.getText().toString();

      try {
         presenter.saveEvent(event);
         Toast.makeText(this, "Thêm sự kiện thành công", Toast.LENGTH_SHORT).show();
      } catch (Exception e) {
         Toast.makeText(this, "Thêm sự kiện thất bại", Toast.LENGTH_SHORT).show();
      }


      this.finish();
   }

   public void selectDate(View view) {
      int day = myCalendar.get(Calendar.DAY_OF_MONTH);
      int month = myCalendar.get(Calendar.MONTH);
      int year = myCalendar.get(Calendar.YEAR);
      DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme, (datePicker, i, i1, i2) -> {
         myCalendar.set(Calendar.YEAR, i);
         myCalendar.set(Calendar.MONTH, i1);
         myCalendar.set(Calendar.DAY_OF_MONTH, i2);
         showDateSelected(myCalendar);
      }, year, month, day);
      datePickerDialog.show();
   }


}