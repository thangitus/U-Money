package com.itus.u_money.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.itus.u_money.R;
import com.itus.u_money.contract.FlashContract;
import com.itus.u_money.presenter.SplashPresenter;

public class SplashActivity extends AppCompatActivity implements FlashContract.View {

   LottieAnimationView animationView;
   FlashContract.Presenter presenter;
   Intent intent;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      presenter = new SplashPresenter(this);
      animationView = findViewById(R.id.animation_view);
      SharedPreferences pref = getApplicationContext().getSharedPreferences("UMoney", 0); // 0 - for private mode
      boolean isFirst = pref.getBoolean("IsFirst", true);
      if (isFirst)
         intent = new Intent(this, StartActivity.class);
      else
         intent = new Intent(this, MainActivity.class);

      animationView.addAnimatorListener(new Animator.AnimatorListener() {
         @Override
         public void onAnimationStart(Animator animator) {

         }
         @Override
         public void onAnimationEnd(Animator animator) {
            startActivity(intent);
            finish();
         }
         @Override
         public void onAnimationCancel(Animator animator) {

         }
         @Override
         public void onAnimationRepeat(Animator animator) {

         }
      });
   }
}