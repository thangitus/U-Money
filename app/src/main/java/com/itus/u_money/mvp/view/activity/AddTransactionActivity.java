package com.itus.u_money.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityAddTransactionBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
   public void selectGroup(View view) {}
   public void selectDate(View view) {
      final Calendar myCalendar = Calendar.getInstance();
      int day = myCalendar.get(Calendar.DAY_OF_MONTH);
      int month = myCalendar.get(Calendar.MONTH);
      int year = myCalendar.get(Calendar.YEAR);
      DatePickerDialog datePickerDialog = new DatePickerDialog(AddTransactionActivity.this, R.style.DialogTheme, (datePicker, i, i1, i2) -> {
         myCalendar.set(Calendar.YEAR, i);
         myCalendar.set(Calendar.MONTH, i1);
         myCalendar.set(Calendar.DAY_OF_MONTH, i2);
         String myFormat = "dd/MM/yyyy";
         SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
         binding.edtDate.setText(sdf.format(myCalendar.getTime()));
         binding.edtDate.invalidate();
         binding.edtDate.requestLayout();
      }, year, month, day);
      datePickerDialog.show();
   }
   public void selectEvent(View view) {}
   public void selectImage(View view) {}
}