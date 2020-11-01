package com.itus.u_money

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.itus.u_money.view.fragment.TransactionFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class FragmentTransactionTest {

    @Test
    fun clickItem() {
        launchFragmentInContainer<TransactionFragment>()
        onView(withId(R.id.recyclerview_list_transaction))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

//        onView(withId(R.id.text_view_name))
//                .check(matches(withText("27")))
//                .check(matches(isDisplayed()))
    }
}