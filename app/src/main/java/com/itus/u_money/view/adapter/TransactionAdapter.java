package com.itus.u_money.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.databinding.ItemTransactionBinding;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
   List<Transaction> transactionList;
   List<TransactionType> transactionTypeList;

   public void setTransactionTypeList(List<TransactionType> transactionTypeList) {
      this.transactionTypeList = transactionTypeList;
   }
   public void setTransactionList(List<Transaction> transactionList) {
      this.transactionList = transactionList;
   }
   public TransactionAdapter(List<Transaction> data) {
      this.transactionList = data;
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
      Transaction transaction = transactionList.get(position);
      TransactionType transactionType = transactionTypeList.get(position);
      holder.bind(transaction, transactionType);
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
      public void bind(Transaction transaction, TransactionType transactionType) {
         binding.iconTransaction.setImageResource(transactionType.iconId);
         binding.textViewName.setText(transactionType.name);
         binding.textViewNote.setText(transaction.note);
         binding.textViewPrice.setText(transaction.amount + " VND");
      }
   }
}
