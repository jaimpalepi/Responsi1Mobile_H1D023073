package com.example.responsi1mobileh1d023073.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023073.R
import com.example.responsi1mobileh1d023073.data.model.Player
import com.example.responsi1mobileh1d023073.ui.PlayerDetailFragment
import com.google.android.material.card.MaterialCardView

class PlayerAdapter(private val players: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: MaterialCardView = itemView.findViewById(R.id.player_card)
        val tvName: TextView = itemView.findViewById(R.id.tv_player_name)
        val tvNationality: TextView = itemView.findViewById(R.id.tv_player_nationality)

        fun bind(player: Player) {
            tvName.text = player.name
            tvNationality.text = player.nationality
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)

        val colorResId = when (player.getPositionCategory()) {
            "Goalkeeper" -> R.color.yellow_goalkeeper
            "Defender" -> R.color.blue_defender
            "Midfielder" -> R.color.green_midfielder
            "Forward" -> R.color.red_forward
            else -> R.color.white
        }
        val color = ContextCompat.getColor(holder.itemView.context, colorResId)
        holder.cardView.setCardBackgroundColor(color)

        holder.itemView.setOnClickListener {
            val activity = holder.itemView.context as AppCompatActivity
            PlayerDetailFragment.newInstance(player).show(activity.supportFragmentManager, "PlayerDetailFragment")
        }
    }
}