package com.itus.u_money.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.itus.u_money.R
import com.itus.u_money.contract.ReportContract
import com.itus.u_money.databinding.FragmentReportBinding
import com.itus.u_money.presenter.ReportPresenter
import com.itus.u_money.view.model.DataBarChart
import com.itus.u_money.view.model.Formatter
import java.util.*

class ReportFragment : Fragment(), ReportContract.View {
    private var chart: BarChart? = null
    private val presenter: ReportContract.Presenter
    var binding: FragmentReportBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false)
        binding!!.presenter = presenter
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initChart()
        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.getData()
    }

    private fun initChart() {
        val chart = activity!!.findViewById<BarChart>(R.id.bar_chart)
        with(chart) {
            extraTopOffset = -30f
            extraBottomOffset = 10f
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            description.isEnabled = false

            // scaling can now only be done on x- and y-axis separately
            setPinchZoom(true)
            setDrawGridBackground(false)
            animateY(500, Easing.EaseInOutQuad)
        }

        with(chart.xAxis) {
            position = XAxis.XAxisPosition.BOTH_SIDED
            setDrawGridLines(false)
            setDrawAxisLine(false)
            granularity = 1f
            setDrawLabels(false)
        }

        with(chart.axisLeft) {
            setDrawLabels(false)
            spaceTop = 25f
            spaceBottom = 25f
            setDrawAxisLine(false)
            setDrawGridLines(false)
            setDrawZeroLine(true) // draw a zero line
            zeroLineColor = Color.GRAY
            zeroLineWidth = 0.7f
        }

        chart.axisRight.isEnabled = false
        chart.legend.isEnabled = false

        this.chart = chart
    }

    override fun updateReportBy(text: String, increase: Boolean) {
        binding!!.textReportBy.text = text
        val animation = if (increase) AnimationUtils.loadAnimation(context, R.anim.up_in)
        else AnimationUtils.loadAnimation(context, R.anim.down_in)

        animation.reset()
        binding!!.textReportBy.clearAnimation()
        binding!!.textReportBy.startAnimation(animation)
    }

    override fun updateDate(text: String) {
        binding!!.tvDate.text = text
    }

    override fun upDateChart(increase: Boolean) {
        val animationOut: Animation
        val animationIn: Animation
        if (increase) {
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
        chart!!.animate()
    }

    override fun updateChartData(dataBarCharts: List<DataBarChart>, animate: Boolean) {
        val values = ArrayList<BarEntry>()
        val colors: MutableList<Int> = ArrayList()
        val green = Color.rgb(110, 190, 102)
        val red = Color.rgb(211, 74, 88)
        for (i in dataBarCharts.indices) {
            val d = dataBarCharts[i]
            val entry = BarEntry(d.xValue, d.yValue)
            values.add(entry)
            if (d.yValue < 0) colors.add(red) else colors.add(green)
        }
        val set: BarDataSet
        set = BarDataSet(values, "Values")
        set.colors = colors
        set.setValueTextColors(colors)
        val data = BarData(set)
        data.setValueTextSize(13f)
        data.setValueFormatter(Formatter())
        data.barWidth = 0.8f
        if (animate) chart!!.animate()
        chart!!.data = data
        chart!!.invalidate()
    }

    override fun updateInOut(`in`: Long, out: Long) {
        binding!!.textIn.text = "$`in` VND"
        binding!!.textOut.text = "$out VND"
    }

    override fun updateLoan(loan: Long, borrow: Long) {
        binding!!.textLoan.text = "$loan VND"
        binding!!.textBorrow.text = "$borrow VND"
    }

    companion object {
        private var mInstance: ReportFragment? = null

        @JvmStatic
        val instance: ReportFragment?
            get() {
                if (mInstance == null) mInstance = ReportFragment()
                return mInstance
            }
    }

    init {
        presenter = ReportPresenter(this)
    }
}