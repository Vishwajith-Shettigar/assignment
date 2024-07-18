package com.example.netclanexplorer.ui.theme.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.Dummydata
import com.example.netclanexplorer.ui.theme.data.dummyData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

  private val _dummyDataList: MutableState<MutableList<Dummydata>> = mutableStateOf(
    mutableListOf()
  )
  val dummydataList: MutableState<MutableList<Dummydata>> get() = _dummyDataList

  init {
    viewModelScope.launch {
      getDummyData()
    }
  }

  private suspend fun getDummyData() {
    delay(1000)
    _dummyDataList.value = (dummyData)
  }
}
