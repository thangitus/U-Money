package com.itus.u_money.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.itus.u_money.R
import com.itus.u_money.model.Icon

class IconAdapter(var listener: IconListener) : RecyclerView.Adapter<IconAdapter.ViewHolder>() {
    var data: List<Icon>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.item_icon, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val icon = data!![position]
        holder.bind(icon)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val imageIcon: ImageButton = view.findViewById(R.id.item_icon)
        init {
            imageIcon.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            listener.onIconClick(data?.get(layoutPosition))
        }

        fun bind(icon: Icon) {
            imageIcon.setImageResource(icon.resourceId)
        }
    }
}