package com.example.socialmedia

import android.graphics.drawable.Drawable
import android.security.keystore.StrongBoxUnavailableException
import androidx.core.graphics.drawable.toDrawable

class SMPost(ID: String , userID: String?, img : Drawable) {

    var user: String?
    var postID : String
    var postImage: Drawable
    var likes : Int

    init {
        user  = userID
        postID = ID
        postImage = img
        likes = 1500
    }

}