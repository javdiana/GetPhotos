package com.javdiana.getphotos.model.sponsorship

data class Sponsorship(
    private val impression_urls:Array<String>,
    private val impressions_id:String,
    private val tagline:String,
    private val sponsor:Sponsor
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Sponsorship

        if (!impression_urls.contentEquals(other.impression_urls)) return false

        return true
    }

    override fun hashCode(): Int {
        return impression_urls.contentHashCode()
    }
}