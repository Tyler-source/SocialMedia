package com.example.socialmedia

import android.app.AlertDialog
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView

class SMPostListAdapter(private val dataSet: ArrayList<SMPost>,
                        private val context: AppCompatActivity,
                        private val onClick: (SMPost) -> Unit) :
    RecyclerView.Adapter<SMPostListAdapter.ViewHolder>() {

        inner class ViewHolder(view:View): RecyclerView.ViewHolder(view){
            var postTitle: TextView
            var pfp: ImageView
            var imagePost: ImageView
            //var numLikes: TextView

            init {
                postTitle = view.findViewById(R.id.postTitle)
                pfp = view.findViewById(R.id.profilePicture)
                imagePost = view.findViewById(R.id.postImage)
               // numLikes = view.findViewById(R.id.likesAmount)W
                view.setOnLongClickListener{

                    //TODO: Download Image

                    false
                }

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.social_media_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postTitle.text = dataSet[position].postTitle
        holder.pfp.setImageResource(R.drawable.avatar)
        holder.imagePost.setImageDrawable(dataSet[position].postImage)


    }

    override fun getItemCount() = dataSet.size

}
