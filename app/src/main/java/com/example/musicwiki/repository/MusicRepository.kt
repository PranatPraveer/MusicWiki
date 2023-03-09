package com.example.musicwiki.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.musicwiki.api.MusicAPI
import com.example.musicwiki.models.*
import com.example.musicwiki.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class MusicRepository @Inject constructor(private val musicAPI: MusicAPI) {

    private val _musicLiveData= MutableLiveData<NetworkResult<Toptags>>()
    val musicLiveData: LiveData<NetworkResult<Toptags>>
        get() = _musicLiveData

    private val _tagInfoLiveData=MutableLiveData<NetworkResult<TagX>>()
    val tagInfoLiveData:LiveData<NetworkResult<TagX>>
        get() = _tagInfoLiveData

    private val _topAlbumsLiveData=MutableLiveData<NetworkResult<TopAlbums>>()
    val topAlbumsLiveData:LiveData<NetworkResult<TopAlbums>>
        get() = _topAlbumsLiveData

    private val _topArtistsLiveData=MutableLiveData<NetworkResult<TopArtists>>()
    val topArtistsLiveData:LiveData<NetworkResult<TopArtists>>
        get() = _topArtistsLiveData

    private val _topTracksLiveData=MutableLiveData<NetworkResult<TopTracks>>()
    val toptracksLiveData:LiveData<NetworkResult<TopTracks>>
        get() = _topTracksLiveData

    private val _AlbumInfoLiveData=MutableLiveData<NetworkResult<AlbumInfo>>()
    val AlbumInfoLiveData:LiveData<NetworkResult<AlbumInfo>>
        get() = _AlbumInfoLiveData

    private val _ArtistInfoLiveData=MutableLiveData<NetworkResult<ArtistInfo>>()
    val ArtistInfoLiveData:LiveData<NetworkResult<ArtistInfo>>
        get() = _ArtistInfoLiveData

    suspend fun getTopTags(){
        _musicLiveData.postValue(NetworkResult.Loading())
        val response= musicAPI.getTopTags()
        if (response.body()!=null && response.isSuccessful){
            _musicLiveData.postValue(NetworkResult.Success(response.body()!!.toptags))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _musicLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _musicLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
    suspend fun getTagInfo(tag:String){
        _tagInfoLiveData.postValue(NetworkResult.Loading())
        val response= musicAPI.getTagInfo(tag)
        if (response.body()!=null && response.isSuccessful){
            _tagInfoLiveData.postValue(NetworkResult.Success(response.body()!!.tag))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _tagInfoLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _tagInfoLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
    suspend fun getTopAlbum(tag: String){
        _topAlbumsLiveData.postValue(NetworkResult.Loading())
        val response= musicAPI.getTopAlbums(tag)
        if (response.body()!=null && response.isSuccessful){
            _topAlbumsLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _topAlbumsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _topAlbumsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
    suspend fun getTagArtists(tag: String){
        _topArtistsLiveData.postValue(NetworkResult.Loading())
        val response= musicAPI.getTagArtists(tag)
        if (response.body()!=null && response.isSuccessful){
            _topArtistsLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _topArtistsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _topArtistsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
    suspend fun getTagTracks(tag: String){
        _topTracksLiveData.postValue(NetworkResult.Loading())
        val response= musicAPI.getTagTracks(tag)
        if (response.body()!=null && response.isSuccessful){
            _topTracksLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _topTracksLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _topTracksLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }

    }
    suspend fun getAlbumInfo(artist:String,album:String){
        _AlbumInfoLiveData.postValue(NetworkResult.Loading())
        val response= musicAPI.getAlbumInfo(artist,album)
        if (response.body()!=null && response.isSuccessful){
            _AlbumInfoLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _AlbumInfoLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _AlbumInfoLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
    suspend fun getArtistInfo(artist: String){
        _ArtistInfoLiveData.postValue(NetworkResult.Loading())
        val response= musicAPI.getArtistInfo(artist)
        if (response.body()!=null && response.isSuccessful){
            _ArtistInfoLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _ArtistInfoLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _ArtistInfoLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}