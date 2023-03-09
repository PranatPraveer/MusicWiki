package com.example.musicwiki.models

data class ArtistXXXX(
    val bio: Bio,
    val image: List<ImageXXXX>,
    val mbid: String,
    val name: String,
    val ontour: String,
    val similar: Similar,
    val stats: Stats,
    val streamable: String,
    val tags: TagsX,
    val url: String
)