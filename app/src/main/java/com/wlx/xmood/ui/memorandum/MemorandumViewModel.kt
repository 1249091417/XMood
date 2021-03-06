package com.wlx.xmood.ui.memorandum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MemorandumViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is memorandum Fragment"
    }
    val text: LiveData<String> = _text

    private val searchNoteLiveData = MutableLiveData<String>()
    private val searchNavTitleLiveData = MutableLiveData<String>()

    val noteList = ArrayList<MemorandumItem>()
    val navTitleList = ArrayList<String>()

    val noteLiveData = Transformations.switchMap(searchNoteLiveData) { query ->
        MemoDataGet.getNote(query)
    }

    val navTitleLiveData = Transformations.switchMap(searchNavTitleLiveData) { query ->
        MemoDataGet.getNavTitle(query)
    }

    fun searchNote(query: String) {
        searchNoteLiveData.value = query
    }

    fun searchNavTitle(query: String) {
        searchNavTitleLiveData.value = query
    }

}