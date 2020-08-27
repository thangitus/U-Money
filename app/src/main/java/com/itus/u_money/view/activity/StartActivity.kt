package com.itus.u_money.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.itus.u_money.contract.StartContract
import com.itus.u_money.databinding.ActivityStartBinding
import com.itus.u_money.presenter.StartPresenter

class StartActivity : AppCompatActivity(), StartContract.View {
    var binding: ActivityStartBinding? = null
    var presenter: StartContract.Presenter = StartPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityStartBinding.inflate(layoutInflater);
        setContentView(binding!!.root)
    }

    override fun onSaveSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun onNextClick() {
        val fullName = binding!!.edtFullName.text.toString()
        val curAmount = binding!!.currentMoney.text.toString().toLong()

        val pref = application.getSharedPreferences("UMoney", 0)
        val editor = pref.edit()
        editor.putBoolean("IsFirst", false)
        editor.putString("FullName", fullName)
        editor.putLong("CurAmount", curAmount)
        editor.apply()
        presenter.saveBudget(binding!!.edtBudget.text.toString().toLong())
    }
}
