package com.itus.u_money.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.itus.u_money.R;
import com.itus.u_money.contract.BudgetListContract;
import com.itus.u_money.databinding.ActivityBudgetListBinding;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Budget;
import com.itus.u_money.model.Icon;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.presenter.BudgetListPresenter;
import com.itus.u_money.view.adapter.BudgetListAdapter;
import com.itus.u_money.view.adapter.PlanOptionAdapter;
import com.itus.u_money.view.model.BudgetItem;
import com.itus.u_money.view.model.PlanOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.itus.u_money.App.getContext;

public class BudgetListActivity extends AppCompatActivity implements BudgetListContract.View {

    ActivityBudgetListBinding binding;
    BudgetListContract.Presenter presenter;
    BudgetListAdapter adapter;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBudgetListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new BudgetListPresenter(this);
        initActionBar();

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setItemAnimator(new DefaultItemAnimator());
        presenter.loadBudgetList();

        adapter = new BudgetListAdapter(this, new ArrayList<>());
        binding.recyclerview.setAdapter(adapter);
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        getSupportActionBar().setTitle("Ngân sách");
    }

    @Override
    public void setData(List<BudgetItem> items) {
        adapter.setBudgetItems(items);
        adapter.notifyDataSetChanged();
    }
}