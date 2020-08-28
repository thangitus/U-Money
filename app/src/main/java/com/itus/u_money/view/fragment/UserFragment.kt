package com.itus.u_money.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.itus.u_money.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private var TAG="UserFragment"

    var binding: FragmentUserBinding? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUserBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = context!!.getSharedPreferences("UMoney", 0) // 0 - for private mode
        val fullName = pref.getString("FullName", "")
        val curAmount = pref.getLong("CurAmount", 0)
        binding!!.edtFullName.setText(fullName)
        binding!!.currentMoney.setText("$curAmount VND")
        binding!!.edtBudget.setText(" ")
    }
}