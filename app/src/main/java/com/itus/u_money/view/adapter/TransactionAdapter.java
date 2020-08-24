package com.itus.u_money.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.databinding.ItemTransactionBinding;
import com.itus.u_money.model.Transaction;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {

   @NonNull
   @Override
   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return null;
   }
   @Override
   public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

   }
   @Override
   public int getItemCount() {
      return 0;
   }

   class MyViewHolder extends RecyclerView.ViewHolder {
      ItemTransactionBinding binding;

      public MyViewHolder(@NonNull ItemTransactionBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }
      public void bind(Transaction transaction) {
      }
   }
}
