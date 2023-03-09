package com.example.musicwiki.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.R
import com.example.musicwiki.databinding.AlbumLayoutBinding
import com.example.musicwiki.fragments.GenreDetailFragment
import com.example.musicwiki.models.Album

class AlbumAdapter(val context: GenreDetailFragment,
    val list: MutableList<Album>
) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {


    inner class  ViewHolder(val binding: AlbumLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AlbumLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album=list[position]
        with(holder){
            with(list[position]) {
                binding.title.text=this.name
                binding.artist.text=album.artist.name
                val i = album.image.size
                Glide.with(context).load(album.image[i-1].text).placeholder(R.drawable.music).into(binding.image)

                binding.root.setOnClickListener {
                    val bundle= Bundle()
                    bundle.putString("ANAME", this.name)
                    bundle.putString("ARNAME", this.artist.name)
                    Navigation.findNavController(it).navigate(R.id.action_genreDetailFragment_to_artistAlbumFragment,bundle)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}