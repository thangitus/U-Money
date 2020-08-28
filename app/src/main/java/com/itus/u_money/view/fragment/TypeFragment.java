package com.itus.u_money.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.contract.TypeContract;
import com.itus.u_money.databinding.FragmentScreenSlideBinding;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.TypePresenter;
import com.itus.u_money.view.adapter.TransactionTypeListener;
import com.itus.u_money.view.adapter.TypeAdapter;

import java.util.List;
import java.util.Objects;

public class TypeFragment extends Fragment implements TransactionTypeListener, TypeContract.View {
   public static final String CHOOSING_TYPE = "CHOOSING_TYPE";
   public static final String ADD_TRANSACTION = "ADD_TRANSACTION";
   private static final String GROUP_INDEX = "GROUP_INDEX";
   public static final String TYPE_SELECTED = "TYPE_SELECTED";
   TypeAdapter typeAdapter;
   FragmentScreenSlideBinding binding;
   TypeContract.Presenter presenter;
   private Intent intent;

   public void setIntent(Intent intent) {
      this.intent = intent;
   }
   private TypeFragment() {
      presenter = new TypePresenter(this);
   }

   public static TypeFragment newInstance(int groupIndex, String choosingType, Intent intent) {
      TypeFragment fragment = new TypeFragment();
      Bundle args = new Bundle();
      args.putInt(GROUP_INDEX, groupIndex);
      args.putString(CHOOSING_TYPE, choosingType);
      fragment.setArguments(args);
      fragment.setIntent(intent);
      return fragment;
   }

   @Nullable
   @Override
   public View onCreateView(
           @NonNull LayoutInflater inflater,
           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = FragmentScreenSlideBinding.inflate(inflater);
      presenter.setType(getArguments().getString(CHOOSING_TYPE));
      assert getArguments() != null;
      presenter.getData(getArguments().getInt(GROUP_INDEX));

      typeAdapter = new TypeAdapter(getContext(), this);
      binding.recyclerview.setAdapter(typeAdapter);
      binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));

      return binding.getRoot();
   }

   @Override
   public void onItemClick(TransactionType transactionType) {
      intent.putExtra(TYPE_SELECTED, transactionType);
      Objects.requireNonNull(getActivity())
             .setResult(getActivity().RESULT_OK,intent);
      getActivity().finish();
   }

   @Override
   public void showData(List<TransactionType> transactionTypeList) {
      typeAdapter.setData(transactionTypeList);
      typeAdapter.notifyDataSetChanged();
   }
}
