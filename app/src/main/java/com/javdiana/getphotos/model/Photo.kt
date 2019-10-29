package com.javdiana.getphotos.model

import com.javdiana.getphotos.model.sponsorship.Sponsorship
import com.javdiana.getphotos.model.user.User

data class Photo(
    val id: String,
    val created_at:String,
    val updated_at: String,
    val promoted_at: String?,
    val width: Int,
    val height:Int,
    val color:String,
    val description:String?,
    val alt_description: String,
    val urls: Urls,
    val links: Links,
    val categories:Array<String>?,
    val likes:Int,
    val liked_by_user:Boolean,
    val current_user_collections:Array<String>?,
    val user: User,
    val sponsorship: Sponsorship
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Photo

        if (categories != null) {
            if (other.categories == null) return false
            if (!categories.contentEquals(other.categories)) return false
        } else if (other.categories != null) return false
        if (current_user_collections != null) {
            if (other.current_user_collections == null) return false
            if (!current_user_collections.contentEquals(other.current_user_collections)) return false
        } else if (other.current_user_collections != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = categories?.contentHashCode() ?: 0
        result = 31 * result + (current_user_collections?.contentHashCode() ?: 0)
        return result
    }
}