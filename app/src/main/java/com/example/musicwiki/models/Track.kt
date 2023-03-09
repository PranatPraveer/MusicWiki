package com.example.musicwiki.models

data class Track(
    val attr: AttrXXXXXX,
    val artist: ArtistXX,
    val duration: String,
    val image: List<ImageXX>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)