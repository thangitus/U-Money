package com.itus.u_money.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itus.u_money.R;
import com.itus.u_money.contract.AddTransactionContract;
import com.itus.u_money.databinding.ActivityAddTransactionBinding;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.AddTransactionPresenter;
import com.itus.u_money.view.fragment.TypeFragment;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class AddTransactionActivity extends AppCompatActivity implements AddTransactionContract.View {
   private static final int REQUEST_CODE_CHOOSE = 1245;
   private static final int REQUEST_CODE = 123;
   private static final int REQUEST_CODE_TYPE = 323;
   ActivityAddTransactionBinding binding;
   AddTransactionContract.Presenter presenter;
   Transaction transaction;
   Calendar myCalendar;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityAddTransactionBinding.inflate(getLayoutInflater());
      presenter = new AddTransactionPresenter(this);
      transaction = new Transaction();
      myCalendar = Calendar.getInstance();

      setContentView(binding.getRoot());
      initActionBar();
   }

   private void initActionBar() {
      setSupportActionBar(binding.toolbar);
      Objects.requireNonNull(getSupportActionBar())
             .setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setDisplayShowTitleEnabled(false);
   }

   public void selectGroup(View view) {
      Intent intent = new Intent(this, ChooseTypeActivity.class);
      intent.putExtra(ChooseTypeActivity.CHOOSING_TYPE, ChooseTypeActivity.ADD_TRANSACTION);
      startActivityForResult(intent, REQUEST_CODE_TYPE);
   }

   public void selectDate(View view) {
      int day = myCalendar.get(Calendar.DAY_OF_MONTH);
      int month = myCalendar.get(Calendar.MONTH);
      int year = myCalendar.get(Calendar.YEAR);
      DatePickerDialog datePickerDialog = new DatePickerDialog(AddTransactionActivity.this, R.style.DialogTheme, (datePicker, i, i1, i2) -> {
         myCalendar.set(Calendar.YEAR, i);
         myCalendar.set(Calendar.MONTH, i1);
         myCalendar.set(Calendar.DAY_OF_MONTH, i2);
         showDateSelected(myCalendar);
      }, year, month, day);
      datePickerDialog.show();
   }
   private void showDateSelected(Calendar myCalendar) {
      String myFormat = "dd/MM/yyyy";
      SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
      binding.edtDate.setText(sdf.format(myCalendar.getTime()));
      binding.edtDate.invalidate();
      binding.edtDate.requestLayout();
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (resultCode == RESULT_OK)
         if (requestCode == REQUEST_CODE_CHOOSE)
            showImgSelected(data);
         else if (requestCode == REQUEST_CODE_TYPE)
            handleChooseTypeResult(data);

   }
   private void handleChooseTypeResult(Intent data) {
      TransactionType transactionType = (TransactionType) data.getSerializableExtra(TypeFragment.TYPE_SELECTED);
      transaction.transactionTypeId = transactionType.id;
      binding.textTypeName.setText(transactionType.name);
      presenter.getResourceId(transactionType.iconId);
   }

   private void showImgSelected(@Nullable Intent data) {
      String fileName = data.getStringExtra("FilePath");
      binding.textSelectImg.setVisibility(View.GONE);
      binding.cardImgSelected.setVisibility(View.VISIBLE);
      binding.imageSelected.setVisibility(View.VISIBLE);
      Glide.with(this)
           .load(new File(fileName))
           .skipMemoryCache(true)
           .diskCacheStrategy(DiskCacheStrategy.NONE)
           .into(binding.imageSelected);

      transaction.imagePath = fileName;
   }

   public void selectEvent(View view) {}
   public void selectImage(View view) {
      if (checkPermission()) {
         Matisse.from(AddTransactionActivity.this)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "com.zhihu.matisse.sample.fileprovider", "test"))
                .maxSelectable(1)
                .theme(R.style.Matisse_Dracula)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .forResult(REQUEST_CODE_CHOOSE);
      }
   }
   private boolean checkPermission() {
      int result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
      if (result == PackageManager.PERMISSION_DENIED) {
         ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
         return false;
      }
      return true;
   }

   @Override
   public void onRequestPermissionsResult(int requestCode,
                                          @NonNull String[] permissions,
                                          @NonNull int[] grantResults) {
      if (requestCode == REQUEST_CODE) {
         if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImage(binding.imageSelected);
         }
      }
   }

   @Override
   public void showIconTransaction(int resourceId) {
      binding.imgSelectGroup.setImageResource(resourceId);
   }
   public void onSaveClick(View view) {
      transaction.date = myCalendar.getTime();
      transaction.amount = Long.parseLong(binding.edtCost.getText()
                                                         .toString());
      transaction.note = binding.edtNote.getText()
                                        .toString();
      presenter.saveTransaction(transaction);
      finish();
   }
}