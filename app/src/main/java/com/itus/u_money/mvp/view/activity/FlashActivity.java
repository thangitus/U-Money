package com.itus.u_money.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.itus.u_money.R;
import com.itus.u_money.mvp.contract.FlashContract;
import com.itus.u_money.mvp.presenter.FlashPresenter;

public class FlashActivity extends AppCompatActivity implements FlashContract.View {

   LottieAnimationView animationView;
   FlashContract.Presenter presenter;
   Intent intent;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_flash);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      presenter = new FlashPresenter(this);
      animationView = findViewById(R.id.animation_view);
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