package com.itus.u_money.mvp.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ItemSummaryBinding;
import com.itus.u_money.mvp.model.TransactionType;

import java.util.List;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.MyViewHoler> {
   List<TransactionType> data;

   public SummaryAdapter(List<TransactionType> data) {
      this.data=data;
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
      return data.size();
   }
   public class MyViewHoler extends RecyclerView.ViewHolder {
      ItemSummaryBinding binding;
      public MyViewHoler(@NonNull ItemSummaryBinding itemView) {
         super(itemView.getRoot());
         this.binding = itemView;
      }

      @SuppressLint("SetTextI18n")
      public void bind(TransactionType transactionType) {
         binding.iconSummary.setImageResource(transactionType.iconId);
         binding.textTotalCost.setText(transactionType.total + " VND");
      }
   }
}
