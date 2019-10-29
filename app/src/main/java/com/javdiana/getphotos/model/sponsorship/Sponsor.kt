package com.javdiana.getphotos.model.sponsorship

import com.javdiana.getphotos.model.user.Links
import com.javdiana.getphotos.model.user.Profile_image

data class Sponsor(
    private val id: String,
    private val updated_at: String,
    private val username: String,
    private val name: String,
    private val first_name: String,
    private val last_name: String,
    private val twitter_username: String,
    private val bio: String,
    private val location: String?,
    private val portfolio_url: String,
    private val links: Links,
    private val profile_image: Profile_image,
    private val total_collections: Int,
    private val total_likes: Int,
    private val total_photos: Int,
    private val accepted_tos: Boolean
)