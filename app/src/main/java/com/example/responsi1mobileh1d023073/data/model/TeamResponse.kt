package com.example.responsi1mobileh1d023073.data.model

data class TeamResponse(
    // Informasi Dasar Tim
    val id: Int,
    val name: String,
    val shortName: String?,
    val tla: String?, // Three Letter Abbreviation, misalnya 'ITF'
    val crest: String?, // URL Logo Klub
    val address: String?,
    val phone: String?,
    val website: String?,
    val email: String?,
    val founded: Int?, // Tahun Didirikan (untuk sejarah)
    val clubColors: String?,
    val venue: String?, // Nama Stadion

    // Data Kunci
    val coach: Coach, // Data Pelatih (wajib dari API)
    val squad: List<Player>, // Data Pemain (wajib dari API)

    // Properti Tambahan yang mungkin ada
    val lastUpdated: String?
)
