package com.itus.u_money.mvp.view.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityMainBinding;
import com.itus.u_money.mvp.model.AppDatabase;
import com.itus.u_money.mvp.model.Transaction;
import com.itus.u_money.mvp.model.dao.TransactionDAO;
import com.itus.u_money.mvp.view.fragment.PlanFragment;
import com.itus.u_money.mvp.view.fragment.ReportFragment;
import com.itus.u_money.mvp.view.fragment.UserFragment;
import com.itus.u_money.mvp.view.fragment.WalletFragment;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
   private static final int ADD_TRANSACTION = 100;
   ActivityMainBinding binding;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityMainBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
      loadFragment(new WalletFragment());
      binding.frameContainer.setOnClickListener(view -> binding.motionLayout.transitionToStart());
      Stetho.initializeWithDefaults(this);

      binding.navigation.setOnNavigationItemSelectedListener(item -> {
         Fragment fragment = null;
         switch (item.getItemId()) {
            case R.id.navigation_null:
               revertMotion();
               break;
            case R.id.navigation_wallet:
               fragment = new WalletFragment();
               break;
            case R.id.navigation_report:
               fragment = new ReportFragment();
               break;
            case R.id.navigation_plan:
               fragment = new PlanFragment();
               break;
            case R.id.navigation_user:
               fragment = new UserFragment();
               break;
         }
         if (fragment != null) {
            binding.motionLayout.transitionToStart();
            loadFragment(fragment);
            return true;
         } else
            return false;
      });
   }

   public void revertMotion() {
      if (binding.motionLayout.getProgress() == 1)
         binding.motionLayout.transitionToStart();
      else
         binding.motionLayout.transitionToEnd();
   }
   private void loadFragment(Fragment fragment) {
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.frame_container, fragment);
      fragmentTransaction.commit();
   }

   public void addTransaction(View view) {
      Intent intent = new Intent(this, AddTransactionActivity.class);
      startActivityForResult(intent, ADD_TRANSACTION);
      binding.motionLayout.transitionToStart();
   }
}