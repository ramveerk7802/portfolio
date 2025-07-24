package com.rvcode.portfolioapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.rvcode.portfolioapp.models.PageData
import com.rvcode.portfolioapp.repositories.PageRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(private val repository: PageRepository): ViewModel() {
//private val repository = PageRepository()
    private val _pages = MutableLiveData<List<PageData>>()
    val pages : LiveData<List<PageData>> get() = _pages
    init {
        viewModelScope.launch{
            _pages.value = repository.getPageDataList()
        }
    }
}