package com.itus.u_money.view.utils;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Binding {

   @BindingAdapter("bindDate")
   public static void bindDate(@NonNull TextView textView, Date date) {
      if (date == null)
         return;
      String myFormat = "dd/MM/yyyy";
      SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
      textView.setText(sdf.format(date.getTime()));
   }

   @BindingAdapter({"bindImage"})
   public static void bindImage(ImageView view, String imageUrl) {
      Glide.with(view.getContext())
           .load(imageUrl)
           .apply(RequestOptions.circleCropTransform())
           .into(view);
   }

}
