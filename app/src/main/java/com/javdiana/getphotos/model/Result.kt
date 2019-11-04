package com.javdiana.getphotos.model

data class Result(
    val total: Int,
    val total_pages: Int,
    val results: Array<Photo>
) {
    fun results(): MutableList<Photo> {
        val photos: MutableList<Photo> = mutableListOf()
        for (photo in results) {
            photos.add(photo)
        }
        return photos
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Result

        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        return results.contentHashCode()
    }
}