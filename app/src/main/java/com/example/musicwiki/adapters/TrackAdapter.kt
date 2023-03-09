package com.example.musicwiki.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.R
import com.example.musicwiki.databinding.ArtistsLayoutBinding
import com.example.musicwiki.databinding.TrackLayoutBinding
import com.example.musicwiki.models.Track

class TrackAdapter(
    val list: MutableList<Track>
) : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    class  ViewHolder(val binding: TrackLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TrackLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track=list[position]
        with(holder){
            with(list[position]) {
                val i = this.image.size
                binding.title.text=this.name
                binding.artist.text=this.artist.name
                Glide.with(binding.image).load(track.image[i-1].text).placeholder(R.drawable.music).into(binding.image)
            }
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}