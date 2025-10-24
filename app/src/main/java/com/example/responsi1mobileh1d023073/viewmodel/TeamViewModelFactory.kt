package com.example.responsi1mobileh1d023073.viewmodel

// Lokasi: com.yournim.responsi1mobile.viewmodel/TeamViewModelFactory.kt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.responsi1mobileh1d023073.data.TeamRepository


class TeamViewModelFactory(private val repository: TeamRepository) : ViewModelProvider.Factory {

    // Override ini untuk menyediakan instance ViewModel dengan parameter Repository
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeamViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}