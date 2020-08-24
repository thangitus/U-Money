package com.itus.u_money.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ItemTypeBinding;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.TransactionType;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHoler> {
   Context context;
   RecyclerViewListener listener;
   List<TransactionType> data;

   public TypeAdapter(Context context, RecyclerViewListener listener, List<TransactionType> transactionTypes) {
      this.context = context;
      this.listener = listener;
      this.data = transactionTypes;
   }

   @NonNull
   @Override
   public MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      ItemTypeBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_type, parent, false);
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

   class MyViewHoler extends RecyclerView.ViewHolder implements View

           .OnClickListener {
      ItemTypeBinding binding;

      public MyViewHoler(@NonNull ItemTypeBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }

      public void bind(TransactionType transactionType) {
         AppDatabase.executorService.execute(()-> {
            binding.icon.setImageResource(AppDatabase.getDatabase(context).iconDAO().getResourceIdFromId(transactionType.iconId));
         });
         binding.textGroupName.setText(transactionType.name);
         binding.getRoot()
                .setOnClickListener(this);
      }
      @Override
      public void onClick(View view) {
         listener.onItemClick(data.get(getLayoutPosition()));
      }
   }
}