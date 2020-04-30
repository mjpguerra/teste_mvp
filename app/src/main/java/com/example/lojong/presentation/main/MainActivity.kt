package com.example.lojong.presentation.main

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import com.example.lojong.R
import com.example.lojong.base.extensions.injectPresenter
import com.example.lojong.base.extensions.isVisible
import com.example.lojong.feature.navigation.bottomnavigation.TabManager
import com.example.lojong.feature.navigation.bottomnavigation.TabNavigable
import com.example.lojong.presentation.fundamentals.FundamentalsFragment
import com.example.lojong.presentation.insight.InsightFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener, MainContract.View,
    TabNavigable {

    override val presenter by injectPresenter(this)
    private val tabManager by lazy { TabManager(this) }

    override val fundamentalsTabContainer: View get() = mainTabFundamentalsContainer
    override val insightTabContainer: View get() = mainTabInsightContainer
    override val bottomNavigationView: BottomNavigationView get() = mainBottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBottomNavigation.setOnNavigationItemSelectedListener(this)

        setupViews()
    }

    private fun setupViews() {
        supportFragmentManager.beginTransaction().let {
            addExtraFragments(it)
            it.commit()
        }
    }

    fun changeActionBar() {
        actionBar?.setBackgroundDrawable(
            ColorDrawable(
                ResourcesCompat.getColor(resources, R.color.colorAccent, null)
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        tabManager.onBackPressed()
    }

    override fun finishActivity() {
        finish()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        tabManager.switchTab(menuItem.itemId)
        return true
    }

    override fun showLoading() {
        mainLoading.isVisible = true
    }

    override fun hideLoading() {
        mainLoading.isVisible = false
    }

    private fun addExtraFragments(fragmentTransaction: FragmentTransaction) {
        fragmentTransaction.add(
            R.id.mainTabFundamentalsContainer,
            FundamentalsFragment.newInstance()
        )
        fragmentTransaction.add(R.id.mainTabInsightContainer, InsightFragment.newInstance())
    }

    companion object {
        fun intent(context: Context) = Intent(context, MainActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_SINGLE_TOP }
    }
}
