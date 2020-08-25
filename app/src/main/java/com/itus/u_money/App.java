package com.itus.u_money;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Icon;
import com.itus.u_money.model.TransactionGroup;
import com.itus.u_money.model.TransactionType;

public class App extends Application {
   private static Context context;
   public static Context getContext() {
      return context;
   }

   @Override
   public void onCreate() {
      super.onCreate();
      context = getApplicationContext();
      initDatabase();
   }

   private void initDatabase() {
      AppDatabase.executorService.execute(() -> {
         // Init icon table
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(1, R.drawable.icon_1_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(2, R.drawable.icon_2_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(3, R.drawable.icon_3_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(4, R.drawable.icon_4_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(5, R.drawable.icon_5_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(6, R.drawable.icon_6_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(7, R.drawable.icon_7_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(8, R.drawable.icon_8_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(9, R.drawable.icon_9_svg, "#9599E2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(10, R.drawable.icon_10_svg, "#9599E2"));

         // Init group table
         AppDatabase.getDatabase(this)
                    .transactionGroupDAO()
                    .insert(new TransactionGroup(0, "Nhóm thu"));
         AppDatabase.getDatabase(this)
                    .transactionGroupDAO()
                    .insert(new TransactionGroup(1, "Nhóm chi"));

         // Init type table
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(1, 0, "Tiền lãi", 0, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(2, 0, "Lương", 0, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(3, 0, "Bán đồ", 0, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(4, 0, "Được tặng", 0, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(5, 0, "Đi vay", 0, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(6, 0, "Thu nợ", 0, 1));

         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(7, 0, "Tất cả các khoản", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(8, 0, "Ăn uống", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(9, 0, "Giải trí", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(10, 0, "Mua sắm", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(11, 0, "Du lịch", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(12, 0, "Sức khỏe", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(13, 0, "Gia đình", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(14, 0, "Cho vay", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(15, 0, "Trả nợ", 1, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(16, 0, "Khác", 1, 1));
      });
   }
}
