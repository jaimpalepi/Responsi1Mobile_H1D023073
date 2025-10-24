package com.example.responsi1mobileh1d023073.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023073.R
import com.example.responsi1mobileh1d023073.adapter.PlayerAdapter
import com.example.responsi1mobileh1d023073.data.TeamRepository
import com.example.responsi1mobileh1d023073.viewmodel.TeamViewModel
import com.example.responsi1mobileh1d023073.viewmodel.TeamViewModelFactory

class SquadActivity : AppCompatActivity() {

    private val viewModel: TeamViewModel by viewModels {
        TeamViewModelFactory(TeamRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squad)

        val rvSquad = findViewById<RecyclerView>(R.id.rv_squad)
        rvSquad.layoutManager = LinearLayoutManager(this)

        viewModel.teamData.observe(this) { teamData ->
            teamData?.squad?.let {
                val adapter = PlayerAdapter(it)
                rvSquad.adapter = adapter
            }
        }
    }
}