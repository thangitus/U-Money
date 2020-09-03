package com.itus.u_money.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itus.u_money.R;
import com.itus.u_money.view.activity.BudgetListActivity;
import com.itus.u_money.view.activity.EventListActivity;
import com.itus.u_money.view.model.PlanOption;

import java.util.List;

public class PlanOptionAdapter extends RecyclerView.Adapter<PlanOptionAdapter.ViewHolder> {
    private Context context;
    private List<PlanOption> planOptions;

    public PlanOptionAdapter(Context context, List<PlanOption> planOptions) {
        this.context = context;
        this.planOptions = planOptions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_plan_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlanOption option = planOptions.get(position);
        holder.icon.setImageResource(option.getIcon());
        holder.title.setText(option.getTitle());
        holder.subTitle.setText(option.getSubTitle());
    }

    @Override
    public int getItemCount() {
        System.out.println(planOptions.size());
        return planOptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView subTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subtitle);

            itemView.setOnClickListener(view -> {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.card_press));

                TextView textView = view.findViewById(R.id.title);

                switch (textView.getText().toString()) {
                    case "Ngân sách":
                        context.startActivity(new Intent(context, BudgetListActivity.class));
                        break;
                    case "Sự kiện":
                        context.startActivity(new Intent(context, EventListActivity.class));
                        break;
                    default:
                        break;
                }

            }
            );
        }

        private void gotoList() {

        }
    }
}
