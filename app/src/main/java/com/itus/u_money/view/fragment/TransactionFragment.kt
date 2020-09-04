package com.itus.u_money.view.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import com.itus.u_money.R
import com.itus.u_money.contract.DetailTransactionContract
import com.itus.u_money.contract.TransactionContract
import com.itus.u_money.databinding.FragmentTransactionBinding
import com.itus.u_money.model.Icon
import com.itus.u_money.model.Transaction
import com.itus.u_money.model.TransactionType
import com.itus.u_money.presenter.TransactionPresenter
import com.itus.u_money.view.activity.DetailTransactionActivity
import com.itus.u_money.view.adapter.SummaryAdapter
import com.itus.u_money.view.adapter.TransactionAdapter
import com.itus.u_money.view.adapter.TransactionListener
import com.itus.u_money.view.utils.SpacingItemDecoration
import java.util.*

class TransactionFragment : Fragment(), TransactionContract.View, TransactionListener {
    private var chart: PieChart? = null
    var binding: FragmentTransactionBinding? = null
    private val presenter: TransactionContract.Presenter
    private var transactionAdapter: TransactionAdapter? = null
    private var summaryAdapter: SummaryAdapter? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)
        binding!!.presenter = presenter
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()
        initRecyclerView(binding!!.recyclerviewListTransaction)
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.getListTransaction()
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        transactionAdapter = TransactionAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = transactionAdapter
        recyclerView.addItemDecoration(SpacingItemDecoration(12))
    }

    private fun initChart() {
        val chart: PieChart = activity!!.findViewById(R.id.chart)
        with(chart) {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
            dragDecelerationFrictionCoef = 0.95f
            isDrawHoleEnabled = false
            setHoleColor(Color.WHITE)
            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)
            rotationAngle = 0f
            // enable rotation of the chart by touch
            isRotationEnabled = true
            isHighlightPerTapEnabled = true
            // chart.spin(2000, 0, 360);
            legend.isEnabled = false
            // entry label styling
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(12f)
        }

        summaryAdapter = SummaryAdapter()
        binding!!.recyclerviewSummary.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding!!.recyclerviewSummary.adapter = summaryAdapter

        this.chart = chart
    }

    override fun updateReportBy(text: String, increase: Boolean) {
        binding!!.textReportBy.text = text
        val animation: Animation = if (increase) AnimationUtils.loadAnimation(context, R.anim.up_in) else AnimationUtils.loadAnimation(context, R.anim.down_in)
        animation.reset()
        binding!!.textReportBy.clearAnimation()
        binding!!.textReportBy.startAnimation(animation)
    }

    override fun updateDate(text: String) {
        binding!!.tvDate.text = text
    }

    override fun upDateChart(next: Boolean) {
        val animationOut: Animation
        val animationIn: Animation
        if (next) {
            animationOut = AnimationUtils.loadAnimation(context, R.anim.right_out)
            animationIn = AnimationUtils.loadAnimation(context, R.anim.left_in)
        } else {
            animationOut = AnimationUtils.loadAnimation(context, R.anim.left_out)
            animationIn = AnimationUtils.loadAnimation(context, R.anim.right_in)
        }
        animationOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                chart!!.clearAnimation()
                chart!!.startAnimation(animationIn)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        chart!!.startAnimation(animationOut)
    }

    override fun animateChart() {
        chart!!.animateY(500, Easing.EaseInOutQuad)
    }

    override fun showTransactionList(transactionList: List<Transaction>, transactionTypes: List<TransactionType>, icons: List<Icon>) {
        transactionAdapter!!.setTransactionList(transactionList)
        transactionAdapter!!.setTransactionTypeList(transactionTypes)
        transactionAdapter!!.setIconList(icons)
        transactionAdapter!!.notifyDataSetChanged()
    }

    override fun updateChartData(transactionTypes: List<TransactionType>, icons: List<Icon>, animate: Boolean) {
        binding!!.recyclerviewListTransaction.removeAllViews()
        summaryAdapter!!.setData(transactionTypes)
        summaryAdapter!!.notifyDataSetChanged()
        val entries = ArrayList<PieEntry>()
        for (i in transactionTypes.indices) {
            val transactionType = transactionTypes[i]
            val name: String? = transactionType.name
            entries.add(PieEntry(transactionType.total.toFloat(), name))
        }
        val dataSet = PieDataSet(entries, "Election Results")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f
        val colors = ArrayList<Int>()
        for (icon in icons) colors.add(Color.parseColor(icon.backgroundColor))
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter(chart))
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        chart!!.data = data
        if (animate) chart!!.animateY(500, Easing.EaseInOutQuad)
        chart!!.highlightValues(null)
        chart!!.invalidate()
    }

    override fun onTransactionClick(transaction: Transaction) {
        val intent = Intent(context, DetailTransactionActivity::class.java)
        intent.putExtra("Transaction", transaction)
        startActivity(intent)
    }

    companion object {
        private var mInstance: TransactionFragment? = null

        @JvmStatic
        val instance: TransactionFragment?
            get() {
                if (mInstance == null) mInstance = TransactionFragment()
                return mInstance
            }
    }

    init {
        presenter = TransactionPresenter(this)
    }
}