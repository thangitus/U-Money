package com.itus.u_money.view.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.databinding.ItemTransactionBinding;
import com.itus.u_money.model.Icon;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
   List<Transaction> transactionList;
   List<TransactionType> transactionTypeList;
   List<Icon> iconList;
   private TransactionListener listener;
   public void setIconList(List<Icon> iconList) {
      this.iconList = iconList;
   }
   public void setTransactionTypeList(List<TransactionType> transactionTypeList) {
      this.transactionTypeList = transactionTypeList;
   }
   public void setTransactionList(List<Transaction> transactionList) {
      this.transactionList = transactionList;
   }
   public TransactionAdapter(TransactionListener listener) {
      this.listener = listener;
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
      Icon icon = iconList.get(position);
      holder.bind(transaction, transactionType, icon);
   }
   @Override
   public int getItemCount() {
      if (transactionList == null)
         return 0;
      return transactionList.size();
   }

   class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      ItemTransactionBinding binding;
      SimpleDateFormat simpleDateFormat;
      public ViewHolder(@NonNull ItemTransactionBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
         simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
         binding.getRoot()
                .setOnClickListener(this);
      }
      public void bind(Transaction transaction, TransactionType transactionType, Icon icon) {
         binding.iconTransaction.setImageResource(icon.resourceId);
         binding.textDate.setText(simpleDateFormat.format(transaction.date));

         binding.textViewName.setText(transactionType.name);
         binding.textViewNote.setText(transaction.note);
         binding.textViewPrice.setText(transaction.amount + " VND");

         if (transactionType.transactionGroupId == 1)
            binding.textViewPrice.setTextColor(Color.BLUE);
         else
            binding.textViewPrice.setTextColor(Color.RED);
      }
      @Override
      public void onClick(View view) {
         listener.onTransactionClick(transactionList.get(getLayoutPosition()));
      }
   }
}
