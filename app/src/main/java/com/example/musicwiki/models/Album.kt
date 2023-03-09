package com.example.musicwiki.models

data class Album(
    val attr: AttrXX,
    val artist: Artist,
    val image: List<Image>,
    val mbid: String,
    val name: String,
    val url: String
)