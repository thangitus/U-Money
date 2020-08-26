package com.itus.u_money.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityBudgetListBinding;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.Budget;
import com.itus.u_money.model.Icon;
import com.itus.u_money.model.Transaction;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.view.adapter.BudgetListAdapter;
import com.itus.u_money.view.adapter.PlanOptionAdapter;
import com.itus.u_money.view.model.BudgetItem;
import com.itus.u_money.view.model.PlanOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.itus.u_money.App.getContext;

public class BudgetListActivity extends AppCompatActivity {

    ActivityBudgetListBinding binding;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBudgetListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initActionBar();

        List<BudgetItem> itemList = new ArrayList<>();

        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));
        itemList.add(new BudgetItem(R.drawable.ic_event, "Ăn uống", 1000));

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setItemAnimator(new DefaultItemAnimator());

        BudgetListAdapter adapter = new BudgetListAdapter(this, itemList);
        binding.recyclerview.setAdapter(adapter);
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        getSupportActionBar().setTitle("Ngân sách");
    }
}