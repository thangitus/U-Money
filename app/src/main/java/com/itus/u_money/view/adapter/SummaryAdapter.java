package com.itus.u_money.view.adapter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.App;
import com.itus.u_money.databinding.ItemSummaryBinding;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.TransactionType;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.MyViewHoler> {
   List<TransactionType> data;

   public void setData(List<TransactionType> data) {
      this.data = data;
   }
   @NonNull
   @Override
   public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      ItemSummaryBinding binding = ItemSummaryBinding.inflate(inflater, parent, false);
      return new MyViewHoler(binding);
   }

   @Override
   public void onBindViewHolder(@NonNull MyViewHoler holder, int position) {
      TransactionType transactionType = data.get(position);
      holder.bind(transactionType);
   }

   @Override
   public int getItemCount() {
      if (data == null)
         return 0;
      return data.size();
   }
   public class MyViewHoler extends RecyclerView.ViewHolder {
      ItemSummaryBinding binding;
      Handler handler = new Handler();

      public MyViewHoler(@NonNull ItemSummaryBinding itemView) {
         super(itemView.getRoot());
         this.binding = itemView;
      }

      @SuppressLint("SetTextI18n")
      public void bind(TransactionType transactionType) {
         AppDatabase.executorService.execute(() -> {
            int resourceId = AppDatabase.getDatabase(App.getContext())
                                        .iconDAO()
                                        .getResourceIdFromIdInt(transactionType.iconId);
            handler.post(() -> {
               binding.iconSummary.setImageResource(resourceId);
            });
         });
         binding.textTotalCost.setText(transactionType.total + " VND");
      }
   }
}
