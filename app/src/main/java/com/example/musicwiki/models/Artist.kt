package com.example.musicwiki.models

data class Artist(
    val mbid: String,
    val name: String,
    val url: String,
    val image: List<Image>
)