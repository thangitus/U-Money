package com.itus.u_money.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.stetho.Stetho;
import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityMainBinding;
import com.itus.u_money.view.fragment.PlanFragment;
import com.itus.u_money.view.fragment.ReportFragment;
import com.itus.u_money.view.fragment.UserFragment;
import com.itus.u_money.view.fragment.TransactionFragment;

public class MainActivity extends AppCompatActivity {
   private static final int ADD_TRANSACTION = 100;
   private static final int ADD_BUDGET = 101;
   private static final int ADD_EVENT = 102;

   ActivityMainBinding binding;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityMainBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());
      loadFragment(TransactionFragment.getInstance());
      binding.frameContainer.setOnClickListener(view -> binding.motionLayout.transitionToStart());
      Stetho.initializeWithDefaults(this);

      binding.navigation.setOnNavigationItemSelectedListener(item -> {
         Fragment fragment = null;
         switch (item.getItemId()) {
            case R.id.navigation_null:
               revertMotion();
               break;
            case R.id.navigation_transaction:
               fragment = TransactionFragment.getInstance();
               break;
            case R.id.navigation_report:
               fragment = ReportFragment.getInstance();
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

   public void addBudget(View view) {
      Intent intent = new Intent(this, AddBudgetActivity.class);
      startActivityForResult(intent, ADD_BUDGET);
      binding.motionLayout.transitionToStart();
   }

   public void addEvent(View view) {
      Intent intent = new Intent(this, AddEventActivity.class);
      startActivityForResult(intent, ADD_EVENT);
      binding.motionLayout.transitionToStart();
   }
}