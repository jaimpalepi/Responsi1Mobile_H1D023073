package com.example.responsi1mobileh1d023073.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.responsi1mobileh1d023073.R
import com.example.responsi1mobileh1d023073.data.TeamRepository
import com.example.responsi1mobileh1d023073.viewmodel.TeamViewModel
import com.example.responsi1mobileh1d023073.viewmodel.TeamViewModelFactory

class CoachActivity : AppCompatActivity() {

    private val viewModel: TeamViewModel by viewModels {
        TeamViewModelFactory(TeamRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coach)

        val ivCoachImage: ImageView = findViewById(R.id.iv_coach_image)
        val tvCoachName: TextView = findViewById(R.id.tv_coach_name)
        val tvCoachDob: TextView = findViewById(R.id.tv_coach_dob)
        val tvCoachNationality: TextView = findViewById(R.id.tv_coach_nationality)

        viewModel.teamData.observe(this) { teamData ->
            teamData?.coach?.let { coach ->
                tvCoachName.text = coach.fullName
                tvCoachDob.text = coach.dateOfBirth
                tvCoachNationality.text = coach.nationality

                ivCoachImage.load(R.drawable.kieranmckenna) {
                    placeholder(R.drawable.ic_launcher_foreground)
                    error(R.drawable.ic_launcher_background)
                }
            }
        }
    }
}
