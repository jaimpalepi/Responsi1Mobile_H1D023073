package com.example.responsi1mobileh1d023073.data

import com.example.responsi1mobileh1d023073.api.RetrofitClient
import com.example.responsi1mobileh1d023073.data.model.TeamResponse
import java.io.IOException

class TeamRepository {

    private val apiService = RetrofitClient.apiService
    private val apiToken = RetrofitClient.API_TOKEN

    private val IPSWICH_TOWN_ID = 349

    suspend fun getIpswichTownData(): TeamResponse? {
        try {
            val response = apiService.getTeamData(IPSWICH_TOWN_ID, apiToken)

            return if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
