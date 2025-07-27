package com.rvcode.portfolioapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rvcode.portfolioapp.models.NavigationItem
import com.rvcode.portfolioapp.repositories.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _navigationItemList = MutableLiveData<List<NavigationItem>>()

    val navigationItemList : LiveData<List<NavigationItem>> get() = _navigationItemList

    init {
        _navigationItemList.value = repository.getNavigationItemList()
    }
}