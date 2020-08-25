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
                    .insert(new Icon(1, R.drawable.ic_type_revenue, "#DB5461"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(2, R.drawable.ic_type_salary, "#307391"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(3, R.drawable.ic_type_sale, "#6C91C2"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(4, R.drawable.ic_type_receive, "#25B7D3"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(5, R.drawable.ic_type_loan, "#B57BA6"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(6, R.drawable.ic_type_collect_debt, "#0E4749"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(7, R.drawable.ic_type_all, "#FF3964"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(8, R.drawable.ic_type_eat, "#D6725F"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(9, R.drawable.ic_type_entertainment, "#425777"));
         AppDatabase.getDatabase(this)
                    .iconDAO()
                    .insert(new Icon(10, R.drawable.ic_type_shopping, "#F37C2A"));
         AppDatabase.getDatabase(this)
                 .iconDAO()
                 .insert(new Icon(11, R.drawable.ic_type_travel, "#6EC2CB"));
         AppDatabase.getDatabase(this)
                 .iconDAO()
                 .insert(new Icon(12, R.drawable.ic_type_health, "#4ABC96"));
         AppDatabase.getDatabase(this)
                 .iconDAO()
                 .insert(new Icon(13, R.drawable.ic_type_family, "#FCB922"));
         AppDatabase.getDatabase(this)
                 .iconDAO()
                 .insert(new Icon(14, R.drawable.ic_type_lend_money, "#1C448E"));
         AppDatabase.getDatabase(this)
                 .iconDAO()
                 .insert(new Icon(15, R.drawable.ic_type_pay_loan, "#D6725F"));
         AppDatabase.getDatabase(this)
                 .iconDAO()
                 .insert(new Icon(16, R.drawable.ic_type_other, "#A0A0A0"));

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
                    .insert(new TransactionType(1, "Tiền lãi", 0, 1));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(2, "Lương", 0, 2));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(3, "Bán đồ", 0, 3));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(4, "Được tặng", 0, 4));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(5, "Đi vay", 0, 5));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(6, "Thu nợ", 0, 6));

         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(7, "Tất cả các khoản", 1, 7));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(8, "Ăn uống", 1, 8));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(9, "Giải trí", 1, 9));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(10, "Mua sắm", 1, 10));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(11, "Du lịch", 1, 11));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(12, "Sức khỏe", 1, 12));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(13, "Gia đình", 1, 13));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(14, "Cho vay", 1, 14));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(15, "Trả nợ", 1, 15));
         AppDatabase.getDatabase(this)
                    .transactionTypeDAO()
                    .insert(new TransactionType(16, "Khác", 1, 16));
      });
   }
}
