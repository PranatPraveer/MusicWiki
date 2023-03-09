package com.example.musicwiki.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.musicwiki.R
import com.example.musicwiki.databinding.FragmentMainBinding
import com.example.musicwiki.utils.NetworkResult
import com.example.musicwiki.viewModels.MainViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel by viewModels<MainViewModel>()
    private var islistexpended: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMainBinding.inflate(inflater,container,false)
        mainViewModel.getTopTags()
        binding.toggleLayout.setOnClickListener {
            islistexpended = !islistexpended
            bindObserver()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (islistexpended) {
            binding.toggleIcon.setImageResource(R.drawable.ic_baseline_expand_less)
        } else {
            binding.toggleIcon.setImageResource(R.drawable.ic_baseline_expand_more)
        }
        binding.chipGroup.removeAllViews()
       bindObserver()
    }

    private fun bindObserver() {
        mainViewModel.musicLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {

                is NetworkResult.Success -> {
                    for (i in 1 until it.data?.tag!!.size) {
                        if (i == 10 && !islistexpended) {
                            break
                        }
                        val tag = it.data.tag.get(i)
                        val chip = Chip(context)
                        chip.text = tag.name

                        //chip.setChipBackgroundColorResource(R.color.chipcolor)

                        chip.setOnClickListener {
                            val bundle=Bundle()
                            bundle.putString("TagName", tag.name)
                            findNavController().navigate(R.id.action_mainFragment_to_genreDetailFragment,bundle)

                            Log.d("pp",tag.name)
                        }
                        binding.chipGroup.addView(chip)

                    }
                    Log.d("pp", it.data.tag.toString())

                }
                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {

                }
            }
        })
    }
}