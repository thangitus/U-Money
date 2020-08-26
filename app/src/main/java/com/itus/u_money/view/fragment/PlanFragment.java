package com.itus.u_money.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.databinding.FragmentPlanBinding;

import com.itus.u_money.R;
import com.itus.u_money.view.model.PlanOption;
import com.itus.u_money.view.adapter.PlanOptionAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlanFragment extends Fragment {
   private FragmentPlanBinding binding;

   public PlanFragment() {
   }

   @Nullable
   @Override
   public View onCreateView(
           @NonNull LayoutInflater inflater,
           @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      binding = FragmentPlanBinding.inflate(getLayoutInflater());

      initActionBar();

      List<PlanOption> planOptions = new ArrayList<>();
      planOptions.add(new PlanOption(R.drawable.icon_14_svg, getResources().getString(R.string.budget), getResources().getString(R.string.budget_description)));
      planOptions.add(new PlanOption(R.drawable.ic_event, getResources().getString(R.string.event), getResources().getString(R.string.event_description)));
      planOptions.add(new PlanOption(R.drawable.ic_bill, getResources().getString(R.string.bill), getResources().getString(R.string.bill_description)));
      planOptions.add(new PlanOption(R.drawable.icon_43_svg, getResources().getString(R.string.recurring_transaction), getResources().getString(R.string.recurring_transaction_description)));

      RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
      binding.recyclerview.setLayoutManager(mLayoutManager);
      binding.recyclerview.setItemAnimator(new DefaultItemAnimator());

      PlanOptionAdapter planOptionAdapter = new PlanOptionAdapter(getContext(), planOptions);
      binding.recyclerview.setAdapter(planOptionAdapter);

      return binding.getRoot();
   }

   private void initActionBar() {
      binding.toolbarTitle.setText(getResources().getString(R.string.planning));
   }
}
