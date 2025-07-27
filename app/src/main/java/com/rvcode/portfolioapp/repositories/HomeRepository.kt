package com.rvcode.portfolioapp.repositories

import com.rvcode.portfolioapp.R
import com.rvcode.portfolioapp.models.NavigationItem
import javax.inject.Inject

class HomeRepository @Inject constructor() {
    private val navigationItemList = listOf<NavigationItem>(
        NavigationItem(icon = R.drawable.man_icon, title = "Profile"),
        NavigationItem(icon = R.drawable.portfolio_navigation_icon, title = "Portfolio"),
        NavigationItem(icon = R.drawable.experience_icon, title = "Experience"),
        NavigationItem(icon = R.drawable.setting_icon, title = "Setting"),
    )

    fun getNavigationItemList() : List<NavigationItem> = navigationItemList
}