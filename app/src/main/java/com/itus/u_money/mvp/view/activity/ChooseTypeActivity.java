package com.itus.u_money.mvp.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itus.u_money.R;
import com.itus.u_money.databinding.ActivityScreenSlideBinding;
import com.itus.u_money.databinding.FragmentScreenSlideBinding;
import com.itus.u_money.mvp.model.TransactionType;
import com.itus.u_money.mvp.view.adapter.RecyclerViewListener;
import com.itus.u_money.mvp.view.adapter.TypeAdapter;
import com.itus.u_money.mvp.view.utils.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChooseTypeActivity extends AppCompatActivity {

    private ActivityScreenSlideBinding binding;
    private String type = "AddTransaction";
    private TransactionType.GROUP_TYPE groupType;
    private int indexSelected;
    private int tab_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScreenSlideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initActionBar();

        if (type.equalsIgnoreCase("AddTransaction")) {
            tab_number = 3;
        } else {
            tab_number = 2;
        }

        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        binding.viewPager.setAdapter(pagerAdapter);

//        Intent intent = getIntent();
//        type = intent.getStringExtra("type");
//        groupType = (TransactionType.GROUP_TYPE) intent.getSerializableExtra("groupType");
//        indexSelected = intent.getIntExtra("selected", -1);

        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.toolbar.setTitle("Chọn nhóm");


    }

    private void initActionBar() {
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar())
                .setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> {
            finish();
        });
    }

    public static class PlaceHolderFragment extends Fragment implements RecyclerViewListener {

        private FragmentScreenSlideBinding binding;

        private static final String GROUP_KEY = "group_key";

        private static final String TYPE_SELECTED = "type_selected";

        private PlaceHolderFragment() {}

        public static PlaceHolderFragment newInstance(int groupIndex) {
            PlaceHolderFragment fragment = new PlaceHolderFragment();
            Bundle args = new Bundle();
            args.putInt(GROUP_KEY, groupIndex);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = FragmentScreenSlideBinding.inflate(inflater);
            List<TransactionType> transactionTypes = new ArrayList<>();

            switch (getArguments().getInt(GROUP_KEY)) {
                case 0:
                    transactionTypes = initDataIncome();
                    break;
                case 1:
                    transactionTypes = initDataLoan();
                    break;
                case 2:
                    transactionTypes = initDataOutgoing();
                    break;
                default:
                    break;
            }

            TypeAdapter typeAdapter = new TypeAdapter(this, transactionTypes);
            binding.recyclerview.setAdapter(typeAdapter);
            binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
            binding.recyclerview.addItemDecoration(new SpacingItemDecoration(6));

            return binding.getRoot();
        }

        private List<TransactionType> initDataIncome() {
            List<TransactionType> transactionTypes = new ArrayList<>();
            transactionTypes.add(new TransactionType("Tiền lãi", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Lương", R.drawable.icon_2_svg));
            transactionTypes.add(new TransactionType("Bán đồ", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Được tặng", R.drawable.icon_1_svg));
            return transactionTypes;
        }
        private List<TransactionType> initDataLoan() {
            List<TransactionType> transactionTypes = new ArrayList<>();
            transactionTypes.add(new TransactionType("Cho vay", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Trả nợ", R.drawable.icon_1_svg));
            return transactionTypes;
        }
        private List<TransactionType> initDataOutgoing() {
            List<TransactionType> transactionTypes = new ArrayList<>();
            transactionTypes.add(new TransactionType("Ăn uống", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Giải trí", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Mua sắm", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Du lịch", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Sức khỏe", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Gia đình", R.drawable.icon_1_svg));
            transactionTypes.add(new TransactionType("Khác", R.drawable.icon_1_svg));
            return transactionTypes;
        }

        @Override
        public void onItemClick(TransactionType transactionType) {
            Intent intent = new Intent();
            intent.putExtra(TYPE_SELECTED, transactionType);
            getActivity().setResult(RESULT_OK);
        }
    }

    public class SectionPagerAdapter extends FragmentPagerAdapter {

        public SectionPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return PlaceHolderFragment.newInstance(position);
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
                    if (type.equalsIgnoreCase("AddTransaction")) {
                        return "Đi vay & Cho vay";
                    } else {
                        return "Cho vay";
                    }
                case 1:
                    return "Khoản chi";
                case 2:
                    return "Khoản thu";
                default:
                    return null;
            }
        }
    }
}