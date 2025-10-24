package com.example.responsi1mobileh1d023073.api

// Lokasi: com.yournim.responsi1mobile.api/FootballApiService.kt

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import com.example.responsi1mobileh1d023073.data.model.TeamResponse // Import model yang sudah Anda buat

interface FootballApiService {

    /**
     * Mengambil data tim (Ipswich Town FC) beserta pelatih dan squad-nya.
     * @param idClub ID klub (misal 345)
     * @param token Token otorisasi API (X-Auth-Token)
     */
    @GET("v4/teams/{idClub}")
    suspend fun getTeamData(
        @Path("idClub") idClub: Int,
        @Header("X-Auth-Token") token: String
    ): Response<TeamResponse>
}