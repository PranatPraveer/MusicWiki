package com.example.musicwiki.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository:MusicRepository):ViewModel() {
    val musicLiveData get() = repository.musicLiveData
    val tagInfoLiveData get() = repository.tagInfoLiveData
    val topAlbumsLiveData get() = repository.topAlbumsLiveData
    val topArtistsLiveData get() = repository.topArtistsLiveData
    val toptracksLiveData get()= repository.toptracksLiveData
    val albumInfoLiveData get()=repository.AlbumInfoLiveData
    val artistInfoLiveData get() = repository.ArtistInfoLiveData
    fun getTopTags(){
        viewModelScope.launch {
            repository.getTopTags()
        }
    }
    fun getTagInfo(tag: String) {
        viewModelScope.launch {
            repository.getTagInfo(tag)
        }
    }
    fun getTopAlbum(tag: String){
        viewModelScope.launch{
            repository.getTopAlbum(tag)
        }
    }
    fun getTagArtists(tag: String){
        viewModelScope.launch {
            repository.getTagArtists(tag)
        }
    }
    fun getTagTracks(tag: String){
        viewModelScope.launch {
            repository.getTagTracks(tag)
        }
    }
    fun getAlbumInfo(artist:String, album:String){
        viewModelScope.launch {
            repository.getAlbumInfo(artist,album)
        }
    }
    fun getArtistInfo(artist:String){
        viewModelScope.launch {
            repository.getArtistInfo(artist)
        }
    }
}