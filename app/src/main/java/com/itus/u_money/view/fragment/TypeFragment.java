package com.itus.u_money.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.R;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.view.adapter.RecyclerViewListener;
import com.itus.u_money.view.adapter.TypeAdapter;
import com.itus.u_money.view.utils.SpacingItemDecoration;

import java.util.List;

public class TypeFragment extends Fragment implements RecyclerViewListener {

   List<TransactionType> transactionTypes;
   int selected;
   RecyclerView recyclerView;
   public static TypeFragment newInstance(List<TransactionType> transactionTypes, int selected) {
      TypeFragment fragment = new TypeFragment();
      fragment.setData(transactionTypes, selected);
      return fragment;
   }

   public void setData(List<TransactionType> transactionTypes, int selected) {
      this.transactionTypes = transactionTypes;
      this.selected = selected;
   }
   public TypeFragment() {
   }

   @Nullable
   @Override
   public View onCreateView(
           @NonNull LayoutInflater inflater,
           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_group, container, false);
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      recyclerView = getActivity().findViewById(R.id.recyclerview);
      TypeAdapter adapter = new TypeAdapter(getContext(), this, transactionTypes);
      recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
      recyclerView.setAdapter(adapter);
      recyclerView.addItemDecoration(new SpacingItemDecoration(16));
   }


   @Override
   public void onItemClick(TransactionType transactionType) {

   }
}
