package com.javdiana.getphotos.model

data class Result(
    val total: Int,
    val total_pages: Int,
    val results: List<Photo>
)