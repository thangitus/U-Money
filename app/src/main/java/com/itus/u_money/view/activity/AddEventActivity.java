package com.itus.u_money.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityAddEventBinding;
import com.itus.u_money.model.Icon;
import com.itus.u_money.view.fragment.ChooseIconDialogFragment;

import java.util.Objects;

public class AddEventActivity extends AppCompatActivity {

   private ActivityAddEventBinding binding;

   public Icon icon;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityAddEventBinding.inflate(getLayoutInflater());
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

   public void pickIcon(View view) {
      DialogFragment dialogFragment = ChooseIconDialogFragment.Companion.newInstance();
      dialogFragment.show(getSupportFragmentManager(), "dialog");
   }
   public void updateIcon(Icon icon) {
      this.icon = icon;
      binding.icon.setImageResource(icon.resourceId);
   }
}