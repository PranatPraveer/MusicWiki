package com.example.musicwiki.api

import com.example.musicwiki.models.*
import com.example.musicwiki.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicAPI {

    @GET("/2.0")
    suspend fun getTopTags(@Query("method") method:String="tag.getTopTags",
        @Query("api_key") apiKey:String=API_KEY,
        @Query("format") format:String="json"
    ): Response<TopTagResponse>

    @GET("/2.0")
    suspend fun getTagInfo(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getInfo",
        @Query("api_key") apiKey:String=API_KEY,
        @Query("format") format:String="json"
    ): Response<TagInfo>

    @GET("/2.0")
    suspend fun getTopAlbums(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopAlbums",
        @Query("api_key") apiKey:String=API_KEY,
        @Query("format") format:String="json"
    ): Response<TopAlbums>

    @GET("/2.0")
    suspend fun getTagArtists(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopArtists",
        @Query("api_key") apiKey:String=API_KEY,
        @Query("format") format:String="json"
    ): Response<TopArtists>

    @GET("/2.0")
    suspend fun getAlbumInfo(
        @Query("artist")artist:String,
        @Query("album")album:String,
        @Query("method") method:String="album.getInfo",
        @Query("api_key") apiKey:String= API_KEY,
        @Query("format") format:String="json"
    ) : Response<AlbumInfo>

    @GET("/2.0")
    suspend fun getArtistInfo(
        @Query("artist")artist:String,
        @Query("method") method:String="artist.getInfo",
        @Query("api_key") apiKey:String= API_KEY,
        @Query("format") format:String="json"
    ) : Response<ArtistInfo>

    @GET("/2.0")
    suspend fun getTagTracks(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopTracks",
        @Query("api_key") apiKey:String=API_KEY,
        @Query("format") format:String="json"
    ): Response<TopTracks>

}