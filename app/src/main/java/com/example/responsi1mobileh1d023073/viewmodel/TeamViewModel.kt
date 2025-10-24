package com.example.responsi1mobileh1d023073.viewmodel

// Lokasi: com.yournim.responsi1mobile.viewmodel/TeamViewModel.kt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.responsi1mobileh1d023073.data.TeamRepository
import com.example.responsi1mobileh1d023073.data.model.TeamResponse
import kotlinx.coroutines.launch

class TeamViewModel(private val repository: TeamRepository) : ViewModel() {

    // LiveData untuk data tim (termasuk coach dan squad)
    private val _teamData = MutableLiveData<TeamResponse?>()
    val teamData: LiveData<TeamResponse?> = _teamData

    // LiveData untuk status loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        // Panggil data saat ViewModel pertama kali dibuat
        fetchTeamData()
    }

    private fun fetchTeamData() {
        _isLoading.value = true // Mulai loading

        // Menggunakan Coroutine untuk memanggil Repository di background
        viewModelScope.launch {
            val data = repository.getIpswichTownData()
            _teamData.postValue(data)
            _isLoading.postValue(false) // Selesai loading
        }
    }
}