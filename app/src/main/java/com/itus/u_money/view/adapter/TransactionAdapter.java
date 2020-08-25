package com.itus.u_money.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.databinding.ItemTransactionBinding;
import com.itus.u_money.model.Transaction;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
   List<Transaction> data;

   public void setData(List<Transaction> data) {
      this.data = data;
   }
   public TransactionAdapter(List<Transaction> data) {
      this.data = data;
   }
   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      ItemTransactionBinding binding = ItemTransactionBinding.inflate(inflater, parent, false);
      return new ViewHolder(binding);
   }
   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      Transaction transaction = data.get(position);
      holder.bind(transaction);
   }
   @Override
   public int getItemCount() {
      return 0;
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      ItemTransactionBinding binding;

      public ViewHolder(@NonNull ItemTransactionBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }
      public void bind(Transaction transaction) {
      }
   }
}
