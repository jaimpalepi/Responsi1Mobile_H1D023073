package com.example.responsi1mobileh1d023073.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    val id: Int?,
    val name: String,
    val position: String?,
    val dateOfBirth: String?,
    val nationality: String?,
    val shirtNumber: Int?,
    val type: String?
) : Parcelable {
    fun getPositionCategory(): String {
        return when (position) {
            "Goalkeeper" -> "Goalkeeper"
            "Defence", "Center-Back", "Left-Back", "Right-Back", "Defensive Midfield" -> "Defender"
            "Midfield", "Central Midfield", "Attacking Midfield", "Left Midfield", "Right Midfield" -> "Midfielder"
            "Offence", "Center-Forward", "Left Winger", "Right Winger", "Secondary Striker" -> "Forward"
            else -> "Unknown"
        }
    }
}
