package com.itus.u_money.mvp.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.itus.u_money.R;
import com.itus.u_money.mvp.view.fragment.PlanFragment;
import com.itus.u_money.mvp.view.fragment.ReportFragment;
import com.itus.u_money.mvp.view.fragment.UserFragment;
import com.itus.u_money.mvp.view.fragment.WalletFragment;

public class MainActivity extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      loadFragment(new WalletFragment());

      Stetho.initializeWithDefaults(this);

      BottomNavigationView navigationView = findViewById(R.id.navigation);
      navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
         @Override
         public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
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
               loadFragment(fragment);
               return true;
            } else
               return false;
         }
      });
   }

   private void loadFragment(Fragment fragment) {
      FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
      fragmentTransaction.replace(R.id.frame_container, fragment);
      fragmentTransaction.commit();
   }
}