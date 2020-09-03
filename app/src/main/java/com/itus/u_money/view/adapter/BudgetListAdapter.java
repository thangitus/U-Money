package com.itus.u_money.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.R;
import com.itus.u_money.view.model.BudgetItem;
import com.itus.u_money.view.model.PlanOption;

import java.util.List;

public class BudgetListAdapter extends RecyclerView.Adapter<BudgetListAdapter.ViewHolder> {
    private Context context;
    private List<BudgetItem> budgetItems;

    public BudgetListAdapter(Context context, List<BudgetItem> budgetItems) {
        this.context = context;
        this.budgetItems = budgetItems;
    }

    public void setBudgetItems(List<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_budget, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BudgetItem item = budgetItems.get(position);
        holder.icon.setImageResource(item.getIcon());
        holder.type.setText(item.getType());
        holder.amount.setText(item.getAmount() + "");
    }

    @Override
    public int getItemCount() {
        return budgetItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView type;
        TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            type = itemView.findViewById(R.id.type);
            amount = itemView.findViewById(R.id.amount);

            itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.card_press));
                    }
                }
            );
        }
    }
}
