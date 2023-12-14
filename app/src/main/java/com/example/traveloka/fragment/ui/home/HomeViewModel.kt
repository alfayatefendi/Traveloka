package com.example.traveloka.fragment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.traveloka.data.DataSource
import com.example.traveloka.utils.TimeFlow
import com.example.traveloka.utils.longToTime
import com.example.traveloka.model.Cities
import com.example.traveloka.model.Hotel
import com.example.traveloka.model.Promo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel: ViewModel() {
    private val dataSource = DataSource

    val listPromoLandscape: LiveData<List<Int>> = DataSource.listPromoLandscape.asLiveData()
    val listHotel: LiveData<List<Hotel>> = DataSource.listHotel.asLiveData()
    val listPromo: LiveData<List<Promo>> = DataSource.listPromo.asLiveData()

    private val _time: MutableStateFlow<String> = MutableStateFlow("00:00:00")
    val time: LiveData<String> = _time.asLiveData()

    val searchQuery = MutableLiveData<Cities>()
    val listHotelByCities: LiveData<List<Hotel>>

    init {
        searchQuery.value = Cities.BALI
        listHotelByCities = Transformations.switchMap(searchQuery) {q ->
            DataSource.listHotelByCities(q).asLiveData()
        }
        setTimeCountDown()
    }
    private fun setTimeCountDown() {
        viewModelScope.launch {
            TimeFlow.create(14400000L, 1000L).collect {
                _time.value = it.longToTime()
            }
        }
    }
}