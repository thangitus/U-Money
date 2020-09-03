package com.itus.u_money.view.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.itus.u_money.contract.EventListContract;
import com.itus.u_money.databinding.ActivityEventListBinding;
import com.itus.u_money.presenter.EventListPresenter;
import com.itus.u_money.view.adapter.EventListAdapter;
import com.itus.u_money.view.model.EventItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventListActivity extends AppCompatActivity implements EventListContract.View {

    ActivityEventListBinding binding;
    EventListContract.Presenter presenter;
    EventListAdapter adapter;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new EventListPresenter(this);
        initActionBar();

        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setItemAnimator(new DefaultItemAnimator());
        presenter.loadEventList();

        adapter = new EventListAdapter(this, new ArrayList<>());
        binding.recyclerview.setAdapter(adapter);
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
        getSupportActionBar().setTitle("Sự kiện");
    }

    @Override
    public void setData(List<EventItem> items) {
        adapter.setEventItems(items);
        adapter.notifyDataSetChanged();
    }
}