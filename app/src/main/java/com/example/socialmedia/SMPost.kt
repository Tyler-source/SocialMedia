package com.example.socialmedia

import android.graphics.drawable.Drawable
import android.security.keystore.StrongBoxUnavailableException
import androidx.core.graphics.drawable.toDrawable

class SMPost(postTitleIN: String,ID: String , userID: String?, img : Drawable) {

    var postTitle: String
    var user: String?
    var postID : String
    var postImage: Drawable
    var likes : Int

    init {
        postTitle = postTitleIN
        user  = userID
        postID = ID
        postImage = img
        likes = 1500
    }

}