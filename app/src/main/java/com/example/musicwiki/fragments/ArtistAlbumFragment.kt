package com.example.musicwiki.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.musicwiki.R
import com.example.musicwiki.databinding.FragmentArtistAlbumBinding
import com.example.musicwiki.databinding.FragmentGenreDetailBinding
import com.example.musicwiki.databinding.FragmentMainBinding
import com.example.musicwiki.utils.NetworkResult
import com.example.musicwiki.viewModels.MainViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistAlbumFragment : Fragment() {

    private val mainViewModel by viewModels<MainViewModel>()
    private var _binding: FragmentArtistAlbumBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentArtistAlbumBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arname = arguments?.getString("ARNAME")
        val aname = arguments?.getString("ANAME")
        val aname1 = arguments?.getString("ANAME1")
        if (arname != null && aname != null && aname1==null) {
            binding.artistTitle.isVisible=true
            mainViewModel.getAlbumInfo(arname!!, aname!!)
            mainViewModel.albumInfoLiveData.observe(viewLifecycleOwner, Observer {
                binding.albumInfoPb.isVisible = false
                when (it) {
                    is NetworkResult.Success -> {
                        binding.albumDesc.text = it.data?.album?.wiki?.summary
                        binding.albumTitle.text = it.data?.album?.name
                        binding.artistTitle.text=it.data?.album?.artist
                        Glide.with(binding.albumImg).load(it.data?.album!!.image[0].text).into(binding.albumImg)
                    }
                    is NetworkResult.Error -> {

                    }
                    is NetworkResult.Loading -> {
                        binding.albumInfoPb.isVisible = true
                    }
                }
            })
        }
        if(aname1!=null){
            binding.artistTitle.isVisible=false
            mainViewModel.getArtistInfo(aname1)
            mainViewModel.artistInfoLiveData.observe(viewLifecycleOwner, Observer {
                binding.albumInfoPb.isVisible = false
                when (it) {
                    is NetworkResult.Success -> {
                        binding.albumDesc.text = it.data?.artist?.bio?.summary
                        binding.albumTitle.text = it.data?.artist?.name
                        Glide.with(binding.albumImg).load(it.data?.artist!!.image[0].text).into(binding.albumImg)
                    }
                    is NetworkResult.Error -> {

                    }
                    is NetworkResult.Loading -> {
                        binding.albumInfoPb.isVisible = true
                    }
                }
            })
        }
    }
}