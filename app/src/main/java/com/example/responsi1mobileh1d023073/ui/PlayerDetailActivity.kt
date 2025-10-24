package com.example.responsi1mobileh1d023073.ui

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.responsi1mobileh1d023073.R
import com.example.responsi1mobileh1d023073.data.model.Player

class PlayerDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val player = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("PLAYER_EXTRA", Player::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Player>("PLAYER_EXTRA")
        }

        if (player != null) {
            findViewById<TextView>(R.id.tv_detail_player_name).text = player.name
            findViewById<TextView>(R.id.tv_detail_player_dob).text = player.dateOfBirth
            findViewById<TextView>(R.id.tv_detail_player_nationality).text = player.nationality
            findViewById<TextView>(R.id.tv_detail_player_position).text = player.position
        }
    }
}