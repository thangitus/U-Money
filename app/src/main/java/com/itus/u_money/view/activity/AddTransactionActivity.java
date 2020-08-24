package com.itus.u_money.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityAddTransactionBinding;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AddTransactionActivity extends AppCompatActivity {
   private static final int REQUEST_CODE_CHOOSE = 1245;
   private static final int REQUEST_CODE = 123;
   private static final int REQUEST_CODE_TYPE = 323;
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
   public void selectGroup(View view) {
      AlertDialog.Builder builder = new AlertDialog.Builder(this);
      builder.setTitle("Chọn nhóm");
      List<String> option = initData();
      CharSequence[] cs = option.toArray(new CharSequence[option.size()]);
      builder.setItems(cs, (dialogInterface, i) -> {
         binding.edtName.setText(option.get(i));
         binding.imgSelectGroup.setImageResource(R.drawable.icon_50_svg);
      });
      AlertDialog dialog = builder.create();
      dialog.show();
   }
   private List<String> initData() {
      List<String> res = new ArrayList<>();
      res.add("Thu nợ");
      res.add("Trả nợ");
      res.add("Cho vay");
      res.add("Trả nợ");

      res.add("Ăn uống");
      res.add("Hóa đơn & Tiện ích");
      res.add("Di chuyển");
      res.add("Mua sắm");
      res.add("Giải trí");
      res.add("Du lịch");
      res.add("Sức khỏe");
      res.add("Gia đình");

      res.add("Tiền lãi");
      res.add("Lương");
      res.add("Bán đồ");

      return res;
   }
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

   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE) {
         String fileName = data.getStringExtra("FilePath");
         binding.textSelectImg.setVisibility(View.GONE);
         binding.cardImgSelected.setVisibility(View.VISIBLE);
         binding.imageSelected.setVisibility(View.VISIBLE);
         Glide.with(this)
              .load(new File(fileName))
              .skipMemoryCache(true)
              .diskCacheStrategy(DiskCacheStrategy.NONE)
              .into(binding.imageSelected);
      }
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
   public void selectIcon(View view) {}
}