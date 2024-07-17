package com.example.netclanexplorer.ui.theme.homepage

import androidx.lifecycle.ViewModel
import com.example.model.Dummydata

class HomeViewModel : ViewModel() {

  private val _dummyDataList: MutableList<Dummydata> = mutableListOf()
  val dummydataList: MutableList<Dummydata> = _dummyDataList

  init {

  }

  private fun getDummyData() {

  }
}
