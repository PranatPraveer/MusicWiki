package com.example.musicwiki.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.R
import com.example.musicwiki.databinding.ArtistsLayoutBinding
import com.example.musicwiki.models.Artist
import com.example.musicwiki.models.ArtistX

class ArtistAdapter(
    val list: MutableList<ArtistX>
): RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {

    inner class  ViewHolder(val binding: ArtistsLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ArtistsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album=list[position]
        with(holder){
            with(list[position]) {
                binding.name.text=this.name
                val i = this.image.size
                Glide.with(binding.image).load(this.image[i-1].text).placeholder(R.drawable.guitar).into(binding.image)
                binding.root.setOnClickListener {
                    val bundle= Bundle()
                    bundle.putString("ANAME1", this.name)
                    Navigation.findNavController(it).navigate(R.id.action_genreDetailFragment_to_artistAlbumFragment,bundle)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}