package com.example.responsi1mobileh1d023073.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.responsi1mobileh1d023073.R
import com.example.responsi1mobileh1d023073.data.model.Player

class PlayerDetailFragment : BottomSheetDialogFragment() {

    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            player = if (Build.VERSION.SDK_INT >= 33) {
                it.getParcelable("PLAYER_EXTRA", Player::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.getParcelable("PLAYER_EXTRA")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player?.let {
            view.findViewById<TextView>(R.id.tv_detail_player_name).text = it.name
            view.findViewById<TextView>(R.id.tv_detail_player_dob).text = it.dateOfBirth
            view.findViewById<TextView>(R.id.tv_detail_player_nationality).text = it.nationality
            view.findViewById<TextView>(R.id.tv_detail_player_position).text = it.position
        }
    }

    companion object {
        fun newInstance(player: Player): PlayerDetailFragment {
            val fragment = PlayerDetailFragment()
            val args = Bundle().apply {
                putParcelable("PLAYER_EXTRA", player)
            }
            fragment.arguments = args
            return fragment
        }
    }
}