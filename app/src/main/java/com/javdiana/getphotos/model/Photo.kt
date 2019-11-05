package com.javdiana.getphotos.model

data class Photo(
    val id: String,
    val urls: Urls,
    val links: Links,
    val alt_descxription: String,
    val created_at: String
)
