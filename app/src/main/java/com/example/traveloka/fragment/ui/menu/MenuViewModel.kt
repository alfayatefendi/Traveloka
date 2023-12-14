package com.example.traveloka.fragment.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.traveloka.data.DataSource
import com.example.traveloka.model.Menu

class MenuViewModel: ViewModel() {

    private val dataSource = DataSource

    val listMenuFirst: LiveData<List<Menu>> = dataSource.listMenuFirst.asLiveData()
    val listMenuSecond: LiveData<List<Menu>> = dataSource.listMenuSecond.asLiveData()
}