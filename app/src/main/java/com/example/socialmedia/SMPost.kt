package com.example.socialmedia

import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable

class SMPost(ID: Int , userID: Int, img : Drawable) {

    var user: Int
    var postID : Int
    var postImage: Drawable
    var likes : Int

    init {
        user  = userID
        postID = ID
        postImage = img
        likes = 1500
    }

}