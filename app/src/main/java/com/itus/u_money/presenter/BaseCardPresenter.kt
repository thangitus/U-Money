package com.itus.u_money.presenter

import com.itus.u_money.contract.BaseCardContract
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

internal abstract class BaseCardPresenter(private val view: BaseCardContract.View) : BaseCardContract.Presenter {
    private var curReportInt = 2

    @JvmField
    protected var curReportBy = REPORT_BY.Month

    @JvmField
    protected var date = Calendar.getInstance()
            .time

    @JvmField
    protected var animateChart = true
    override fun onUpClick() {
        animateChart = true
        if (curReportInt == 3) curReportInt = 1 else curReportInt++
        view.updateReportBy(reportBy, true)
        view.updateDate(getCurrentFormatDay().format(date))
        view.animateChart()
    }

    override fun onDownClick() {
        animateChart = true
        if (curReportInt == 1) curReportInt = 3 else curReportInt--
        view.updateReportBy(reportBy, false)
        view.updateDate(getCurrentFormatDay().format(date))
        view.animateChart()
    }

    override fun onViewCreated() {
        view.updateReportBy(reportBy, false)
        view.updateDate(getCurrentFormatDay().format(date))
    }

    override fun onNextClick() {
        updateDate(1)
        animateChart = false
        view.updateDate(getCurrentFormatDay().format(date))
        view.upDateChart(true)
    }

    override fun onPrevClick() {
        updateDate(-1)
        animateChart = false
        view.updateDate(getCurrentFormatDay().format(date))
        view.upDateChart(false)
    }

    override fun onMoveCurDateClick() {
        animateChart = false
        val date = Calendar.getInstance()
                .time
        val compare = this.date.compareTo(date)
        view.updateDate(getCurrentFormatDay().format(date))
        if (compare > 0) view.upDateChart(false) else view.upDateChart(true)
        this.date = date
    }

    private fun updateDate(value: Int) {
        var localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        localDate = when (curReportBy) {
            REPORT_BY.Day -> localDate.plusDays(value.toLong())
            REPORT_BY.Month -> localDate.plusMonths(value.toLong())
            REPORT_BY.Year -> localDate.plusYears(value.toLong())
        }
        date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault())
                .toInstant())
    }

    enum class REPORT_BY {
        Day, Month, Year
    }

    private val reportBy: String
        get() = when (curReportInt) {
            1 -> {
                curReportBy = REPORT_BY.Day
                "Ngày"
            }
            2 -> {
                curReportBy = REPORT_BY.Month
                "Tháng"
            }
            3 -> {
                curReportBy = REPORT_BY.Year
                "Năm"
            }
            else -> "Err"
        }

    private fun getCurrentFormatDay(): SimpleDateFormat {
        val myFormat: String = when (curReportBy) {
            REPORT_BY.Day -> "dd-MM-yyyy"
            REPORT_BY.Month -> "MM-yyyy"
            else -> "yyyy"
        }
        return SimpleDateFormat(myFormat, Locale.US)
    }

    protected fun getStartDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.time = date
        when (curReportBy) {
            REPORT_BY.Day -> {
                calendar[Calendar.HOUR_OF_DAY] = 0
                calendar[Calendar.MINUTE] = 0
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MILLISECOND] = 0
            }
            REPORT_BY.Month -> calendar[Calendar.DAY_OF_MONTH] = 1
            else -> {
                calendar[Calendar.DAY_OF_MONTH] = 1
                calendar[Calendar.MONTH] = 1
            }
        }
        return calendar.time
                .time
    }

    protected fun getEndDay(): Long {
        val calendar = Calendar.getInstance()
        calendar.time = date
        when (curReportBy) {
            REPORT_BY.Day -> {
                calendar[Calendar.HOUR_OF_DAY] = 24
                calendar[Calendar.MINUTE] = 59
                calendar[Calendar.SECOND] = 59
                calendar[Calendar.MILLISECOND] = 99
            }
            REPORT_BY.Month -> calendar[Calendar.DAY_OF_MONTH] = 30
            else -> {
                calendar[Calendar.DAY_OF_MONTH] = 12
                calendar[Calendar.MONTH] = 31
            }
        }
        return calendar.time
                .time
    }
}