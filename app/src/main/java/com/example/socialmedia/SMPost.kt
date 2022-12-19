package com.example.socialmedia

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable

class SMPost(ID: Int , userID: Int) {

    var user: Int
    var postID : Int
    var postImage: Drawable
    var likes : Int

    init {
        user  = userID
        postID = ID
        postImage = R.drawable.mountains.toDrawable()
        likes = 1500
    }

}