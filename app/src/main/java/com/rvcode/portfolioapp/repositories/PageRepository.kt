package com.rvcode.portfolioapp.repositories

import com.rvcode.portfolioapp.R
import com.rvcode.portfolioapp.models.PageData
import javax.inject.Inject




class PageRepository @Inject constructor()  {
    private val pageDataList = listOf(
        PageData(icon = R.drawable.personal_info_icon, "\uD83D\uDC4B Hello", "I am Ramveer","born in Kanpur Uttar Pradesh,Studied CS in National Institute Of Technology Karnataka"),
        PageData(icon = R.drawable.developer_mobile_icon, "Background", "Android Developer","specializing in user interfaces, and published two apps on the Google Play Store."),
        PageData(icon = R.drawable.portfolio_icon, "Passion", "My portfolio","I built this app to show my skills and passion for design and development."),
    )

     fun getPageDataList(): List<PageData> = pageDataList

}