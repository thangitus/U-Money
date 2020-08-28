package com.itus.u_money.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itus.u_money.R
import com.itus.u_money.model.AppDatabase
import com.itus.u_money.model.Icon
import com.itus.u_money.view.activity.AddEventActivity
import com.itus.u_money.view.adapter.IconAdapter
import com.itus.u_money.view.adapter.IconListener

class ChooseIconDialogFragment : DialogFragment(), IconListener {
    private var imageButton: ImageButton? = null

    private var recyclerView: RecyclerView? = null
    private var iconAdapter = IconAdapter(this)
    private var dataIcon: LiveData<List<Icon>>? = null

    override fun onStart() {
        super.onStart()
        val window = dialog!!.window
        window!!.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        window.attributes.windowAnimations = R.style.DialogAnimation
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog!!.window!!.attributes.windowAnimations = R.style.DialogAnimation
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_icon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButton = view.findViewById(R.id.buttonCloseDialog)
        recyclerView = view.findViewById(R.id.recyclerview_icon)
        imageButton!!.setOnClickListener { dialog!!.dismiss() }
        recyclerView!!.adapter = iconAdapter
        recyclerView!!.layoutManager = GridLayoutManager(context, 4);
        getDataIcon();
    }

    private fun getDataIcon() {
        val iconDao = AppDatabase.getDatabase(context).iconDAO()
        dataIcon = iconDao.all
        dataIcon!!.observe(this, { data ->
            iconAdapter.data = data
            iconAdapter.notifyDataSetChanged()
        })
    }

    override fun onIconClick(icon: Icon?) {
        val activity = requireActivity()
        if (activity is AddEventActivity)
            activity.updateIcon(icon)
        dismiss()
    }

    companion object {
        fun newInstance(): ChooseIconDialogFragment {
            return ChooseIconDialogFragment()
        }
    }
}