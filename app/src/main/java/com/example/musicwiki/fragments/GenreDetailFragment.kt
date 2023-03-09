package com.example.musicwiki.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.R
import com.example.musicwiki.adapters.AlbumAdapter
import com.example.musicwiki.adapters.ArtistAdapter
import com.example.musicwiki.adapters.TrackAdapter
import com.example.musicwiki.databinding.FragmentGenreDetailBinding
import com.example.musicwiki.models.Album
import com.example.musicwiki.models.Artist
import com.example.musicwiki.models.ArtistX
import com.example.musicwiki.models.Track
import com.example.musicwiki.utils.NetworkResult
import com.example.musicwiki.viewModels.MainViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreDetailFragment : Fragment() {

    private var _binding2: FragmentGenreDetailBinding? = null
    private val binding2 get() = _binding2!!
    var albums: MutableList<Album> = mutableListOf()
    var artist: MutableList<ArtistX> = mutableListOf()
    var track: MutableList<Track> = mutableListOf()
    private val mainViewModel by viewModels<MainViewModel>()
    val albumAdapter: AlbumAdapter = AlbumAdapter(this,albums)
    val artistAdapter: ArtistAdapter= ArtistAdapter(artist)
    val trackAdapter:TrackAdapter= TrackAdapter(track)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding2=FragmentGenreDetailBinding.inflate(inflater,container,false)

        binding2.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding2.recyclerView.adapter = albumAdapter
        return binding2.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tag=arguments?.getString("TagName")
        binding2.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.text!!.equals("ALBUMS")) {
                   binding2.recyclerView.adapter = albumAdapter
                } else if (tab.text!!.equals("ARTISTS")) {
                  binding2.recyclerView.adapter = artistAdapter
                } else if (tab.text!!.equals("TRACKS")) {
                    binding2.recyclerView.adapter = trackAdapter
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
    })
        getTagInfo(tag!!)
        getTopAlbums(tag)
        getTopArtists(tag)
        getTopTracks(tag)
 }

    private fun getTopTracks(tag: String) {
        mainViewModel.getTagTracks(tag)
        mainViewModel.toptracksLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success -> {
                    track.addAll(it.data?.tracks!!.track)
                    trackAdapter.notifyDataSetChanged()
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }

        })
    }

    private fun getTopArtists(tag: String) {
        mainViewModel.getTagArtists(tag)
        mainViewModel.topArtistsLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success -> {
                    artist.addAll(it.data?.topartists!!.artist)
                    artistAdapter.notifyDataSetChanged()
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }

        })
    }

    private fun getTopAlbums(tag: String) {
        mainViewModel.getTopAlbum(tag)
        mainViewModel.topAlbumsLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success -> {
                    albums.addAll(it.data?.albums?.album!!)
                    albumAdapter.notifyDataSetChanged()
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }

        })
    }

    private fun getTagInfo(tag:String) {
        mainViewModel.getTagInfo(tag)
        mainViewModel.tagInfoLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success -> {
                    binding2.tagDescription.text = it.data?.wiki?.summary
                    binding2.tagTitle.text = it.data?.name
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }

        })

    }
}