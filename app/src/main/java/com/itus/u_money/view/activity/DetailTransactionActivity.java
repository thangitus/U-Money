package com.itus.u_money.view.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itus.u_money.App;
import com.itus.u_money.R;
import com.itus.u_money.contract.DetailTransactionContract;
import com.itus.u_money.databinding.ActivityTransactionDetailBinding;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.DetailTransactionPresenter;
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

public class DetailTransactionActivity extends AppCompatActivity implements DetailTransactionContract.View {
   private static final int REQUEST_CODE_CHOOSE = 1245;
   private static final int REQUEST_CODE = 123;
   private static final int REQUEST_CODE_TYPE = 323;
   ActivityTransactionDetailBinding binding;
   DetailTransactionContract.Presenter presenter;
   Transaction transaction;
   Calendar myCalendar;
   Handler handler = new Handler();

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityTransactionDetailBinding.inflate(getLayoutInflater());
      presenter = new DetailTransactionPresenter(this);
      initActionBar();
      Intent intent = getIntent();
      transaction = (Transaction) intent.getSerializableExtra("Transaction");
      if (transaction != null) {
         binding.setTransaction(transaction);
         binding.title.setText("Chi tiết giao dịch");
         AppDatabase.executorService.execute(() -> {
            AppDatabase appDatabase = AppDatabase.getDatabase(App.getContext());
            TransactionType transactionType = appDatabase.transactionTypeDAO()
                                                         .getById(transaction.transactionTypeId);
            int resourceId = appDatabase.iconDAO()
                                        .getResourceIdFromIdInt(transactionType.iconId);
            handler.post(() -> {
               binding.imgSelectGroup.setImageResource(resourceId);
               binding.textTypeName.setText(transactionType.name);
            });
         });

      } else
         transaction = new Transaction();
      myCalendar = Calendar.getInstance();

      setContentView(binding.getRoot());
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
      DatePickerDialog datePickerDialog = new DatePickerDialog(DetailTransactionActivity.this, R.style.DialogTheme, (datePicker, i, i1, i2) -> {
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
         Matisse.from(DetailTransactionActivity.this)
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
      String amount = binding.edtCost.getText()
                                     .toString();
      if (!amount.equals(""))
         transaction.amount = Long.parseLong(amount);

      transaction.note = binding.edtNote.getText()
                                        .toString();
      if (transaction.transactionTypeId == 0)
         transaction.transactionTypeId = 7;

      presenter.saveTransaction(transaction);

      finish();
   }
}