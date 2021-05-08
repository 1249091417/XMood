package com.wlx.xmood.ui.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DailyViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is daily Fragment"
    }
    val text: LiveData<String> = _text

    private val searchLiveData = MutableLiveData<String>()

    val eventList = ArrayList<DailyItem>()

    val eventLiveData = Transformations.switchMap(searchLiveData) { query ->
        DailyDataGet.getEvent(query)
    }

    fun searchEvent(query: String) {
        searchLiveData.value = query
    }
}