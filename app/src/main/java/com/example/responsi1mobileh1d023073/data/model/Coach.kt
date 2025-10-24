package com.example.responsi1mobileh1d023073.data.model

data class Coach(
    val id: Int?,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val position: String?
) {
    val fullName: String
        get() = "${firstName.orEmpty()} ${lastName.orEmpty()}".trim()
}
