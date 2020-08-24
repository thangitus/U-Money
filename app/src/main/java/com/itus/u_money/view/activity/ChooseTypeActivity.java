package com.itus.u_money.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityScreenSlideBinding;
import com.itus.u_money.databinding.FragmentScreenSlideBinding;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.view.adapter.RecyclerViewListener;
import com.itus.u_money.view.adapter.TypeAdapter;
import com.itus.u_money.view.utils.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChooseTypeActivity extends AppCompatActivity {

    public static final String CHOOSING_TYPE = "CHOOSING_TYPE";
    public static final String ADD_TRANSACTION = "ADD_TRANSACTION";

    private ActivityScreenSlideBinding binding;
    private String currentChoosingType;
    private int tab_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreenSlideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initActionBar();

        Intent intent = getIntent();
        currentChoosingType = intent.getStringExtra(CHOOSING_TYPE);

        assert currentChoosingType != null;
        if (currentChoosingType.equalsIgnoreCase(ADD_TRANSACTION)) {
            tab_number = 2;
        } else {
            tab_number = 1;
        }

        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(pagerAdapter);

        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.toolbar.setTitle("Chọn nhóm");
    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> finish());
    }

    public static class PlaceHolderFragment extends Fragment implements RecyclerViewListener {

        private static final String GROUP_INDEX = "GROUP_INDEX";

        private static final String TYPE_SELECTED = "TYPE_SELECTED";

        private PlaceHolderFragment() {}

        public static PlaceHolderFragment newInstance(int groupIndex, String choosingType) {
            PlaceHolderFragment fragment = new PlaceHolderFragment();
            Bundle args = new Bundle();
            args.putInt(GROUP_INDEX, groupIndex);
            args.putString(CHOOSING_TYPE, choosingType);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            FragmentScreenSlideBinding binding = FragmentScreenSlideBinding.inflate(inflater);
            List<TransactionType> transactionTypes = new ArrayList<>();
            assert getArguments() != null;
            switch (getArguments().getInt(GROUP_INDEX)) {
                case 0:
                    transactionTypes = initDataOutgoing(Objects.requireNonNull(getArguments().getString(CHOOSING_TYPE)));
                    break;
                case 1:
                    transactionTypes = initDataIncome();
                    break;
                default:
                    break;
            }

            TypeAdapter typeAdapter = new TypeAdapter(getContext(), this, transactionTypes);
            binding.recyclerview.setAdapter(typeAdapter);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            return binding.getRoot();
        }

        private List<TransactionType> initDataIncome() {
            List<TransactionType> transactionTypes = new ArrayList<>();
            transactionTypes.add(new TransactionType(1,0,"Tiền lãi", 1, 1));
            transactionTypes.add(new TransactionType(2, 0, "Lương", 1, 1));
            transactionTypes.add(new TransactionType(3,0,"Bán đồ", 1, 1));
            transactionTypes.add(new TransactionType(4, 0,"Được tặng", 1, 1));
            transactionTypes.add(new TransactionType(5, 0,"Đi vay", 1, 1));
            transactionTypes.add(new TransactionType(6, 0,"Thu nợ", 1, 1));
            return transactionTypes;
        }

        private List<TransactionType> initDataOutgoing(String choosingType) {
            List<TransactionType> transactionTypes = new ArrayList<>();
            if (!choosingType.equalsIgnoreCase(ADD_TRANSACTION))
                transactionTypes.add(new TransactionType(7,0,"Tất cả các khoản", 2, 1));

            transactionTypes.add(new TransactionType(8,0,"Ăn uống", 2, 1));
            transactionTypes.add(new TransactionType(9,0,"Giải trí", 2, 1));
            transactionTypes.add(new TransactionType(10,0,"Mua sắm", 2, 1));
            transactionTypes.add(new TransactionType(11,0,"Du lịch", 2, 1));
            transactionTypes.add(new TransactionType(12,0,"Sức khỏe", 2, 1));
            transactionTypes.add(new TransactionType(13,0,"Gia đình", 2, 1));
            transactionTypes.add(new TransactionType(14,0,"Cho vay", 2, 1));
            transactionTypes.add(new TransactionType(15,0,"Trả nợ", 2, 1));
            transactionTypes.add(new TransactionType(16,0,"Khác", 2, 1));

            return transactionTypes;
        }

        @Override
        public void onItemClick(TransactionType transactionType) {
            Intent intent = new Intent();
            intent.putExtra(TYPE_SELECTED, transactionType);
            Objects.requireNonNull(getActivity()).setResult(RESULT_OK);
        }
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PlaceHolderFragment.newInstance(position, currentChoosingType);
        }

        @Override
        public int getCount() {
            return tab_number;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Khoản chi";
                case 1:
                    return "Khoản thu";
                default:
                    return null;
            }
        }
    }
}