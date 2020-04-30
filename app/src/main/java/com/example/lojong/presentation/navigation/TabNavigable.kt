package com.example.lojong.feature.navigation.bottomnavigation

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView

interface TabNavigable {
    val fundamentalsTabContainer: View
    val insightTabContainer: View
    val bottomNavigationView : BottomNavigationView
    fun finishActivity()
}