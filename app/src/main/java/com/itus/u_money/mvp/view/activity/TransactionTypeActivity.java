package com.itus.u_money.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityTransactionTypeBinding;
import com.itus.u_money.mvp.model.TransactionType;
import com.itus.u_money.mvp.view.adapter.ViewPagerAdapter;
import com.itus.u_money.mvp.view.fragment.TypeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionTypeActivity extends AppCompatActivity {
   private static final String TAG = "TransactionTypeActivity";
   String type;
   TransactionType.GROUP_TYPE groupType;
   int indexSelected;
   ActivityTransactionTypeBinding binding;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      binding = ActivityTransactionTypeBinding.inflate(getLayoutInflater());
      setContentView(binding.getRoot());

      Intent intent = getIntent();
      type = intent.getStringExtra("type");
      groupType = (TransactionType.GROUP_TYPE) intent.getSerializableExtra("groupType");
      indexSelected = intent.getIntExtra("selected", -1);

      binding.tabs.setupWithViewPager(binding.viewpager);
      initUI();
      initActionBar();
   }

   private void initActionBar() {
      setSupportActionBar(binding.toolbar);
      Objects.requireNonNull(getSupportActionBar())
             .setDisplayHomeAsUpEnabled(true);
      binding.toolbar.setNavigationOnClickListener(view -> {
         finish();
      });
   }

   private void initUI() {
      ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
      List<TransactionType> dataLoan, dataOutgoing, dataInCome;
      dataLoan = initDataLoan();
      if (type.equalsIgnoreCase("AddTransaction"))
         adapter.addFrag(TypeFragment.newInstance(dataLoan, indexSelected), "Đi vay & Cho vay");
      else
         adapter.addFrag(TypeFragment.newInstance(dataLoan, indexSelected), "Cho vay");

      dataOutgoing = initDataOutgoing();
      adapter.addFrag(TypeFragment.newInstance(dataOutgoing, indexSelected), "Khoản chi");
      if (type.equalsIgnoreCase("AddTransaction")) {
         dataInCome = intDataIncome();
         adapter.addFrag(TypeFragment.newInstance(dataInCome, indexSelected), "Khoản thu");
      }
//      binding.viewpager.setOffscreenPageLimit(2);
      binding.viewpager.setAdapter(adapter);

   }

   private List<TransactionType> intDataIncome() {
      List<TransactionType> transactionTypes = new ArrayList<>();
      transactionTypes.add(new TransactionType("Tiền lãi", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Lương", R.drawable.icon_2_svg));
      transactionTypes.add(new TransactionType("Bán đồ", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Được tặng", R.drawable.icon_1_svg));
      return transactionTypes;
   }
   private List<TransactionType> initDataLoan() {
      List<TransactionType> transactionTypes = new ArrayList<>();
      transactionTypes.add(new TransactionType("Cho vay", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Trả nợ", R.drawable.icon_1_svg));

      if (type.equalsIgnoreCase("AddTransaction")) {
         transactionTypes.add(new TransactionType("Đi vay", R.drawable.icon_1_svg));
         transactionTypes.add(new TransactionType("Thu nợ", R.drawable.icon_1_svg));
      }

      return transactionTypes;
   }
   private List<TransactionType> initDataOutgoing() {
      List<TransactionType> transactionTypes = new ArrayList<>();
      if (!type.equalsIgnoreCase("AddTransaction"))
         transactionTypes.add(new TransactionType("Tất cả các khoản", R.drawable.icon_1_svg));

      transactionTypes.add(new TransactionType("Ăn uống", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Giải trí", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Mua sắm", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Du lịch", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Sức khỏe", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Gia đình", R.drawable.icon_1_svg));
      transactionTypes.add(new TransactionType("Khác", R.drawable.icon_1_svg));

      return transactionTypes;
   }
}