package com.example.netclanexplorer.ui.theme.data

import com.example.model.Dummydata
import com.example.model.HobbyList
import com.example.netclanexplorer.R

val dummyData = mutableListOf<Dummydata>(
  Dummydata.newBuilder().apply {
    this.fullName = "Ash ketchum"
    this.placeAndOccupation = "Kanto | Trainer"
    this.imageUrl = R.drawable.img
    this.distance = 3.7f
    this.message = "Hi Community I am open to new connections"
    this.hobbyList = HobbyList.newBuilder().apply {
      this.addAllHoby(mutableListOf("Coffee", "Business"))
    }.build()
    this.profileScore = 78
  }.build(),
  Dummydata.newBuilder().apply {
    this.fullName = "Serena"
    this.placeAndOccupation = "Tokyo | Writer"
    this.distance = 9.7f
    this.message = "Hi Community I am open to new connections"
    this.hobbyList = HobbyList.newBuilder().apply {
      this.addAllHoby(mutableListOf("Coffee", "Business", "Friendship"))
    }.build()
    this.profileScore = 90
  }.build(),
  Dummydata.newBuilder().apply {
    this.fullName = "Mark"
    this.placeAndOccupation = "Sydney | IT"
    this.distance = 87.7f
    this.message = "Hi Community I am open to new connections"
    this.hobbyList = HobbyList.newBuilder().apply {
      this.addAllHoby(mutableListOf("Coffee", "Business", "Friendship"))
    }.build()
    this.profileScore = 100
  }.build(),
  Dummydata.newBuilder().apply {
    this.fullName = "Michelle"
    this.placeAndOccupation = "Ohio | Student"
    this.distance = 91.7f
    this.message = "Hi Community I am open to new connections"
    this.hobbyList = HobbyList.newBuilder().apply {
      this.addAllHoby(mutableListOf("Coffee"))
    }.build()
    this.profileScore = 20
  }.build()
)