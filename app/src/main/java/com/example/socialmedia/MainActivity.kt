package com.example.socialmedia

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {

    var storage = Firebase.storage
    private lateinit var recyclerView: RecyclerView
    lateinit var dataset: ArrayList<SMPost>


    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var drw = resources.getDrawable(R.drawable.mountains)

        var post1 : SMPost = SMPost(0,0,drw)
        drw = resources.getDrawable(R.drawable.rivers)
        var post2 : SMPost = SMPost(1,1,drw)
        drw = resources.getDrawable(R.drawable.rivers_foreground)
        var post3 : SMPost = SMPost(2,2,drw)

        dataset = arrayListOf(post1,post2,post3)
        val adapter = SMPostListAdapter(dataset,this,this::adapterOnClick)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager




// Register observers to listen for when the download is done or if it failed
        println("This is a new comment")
    }
    private fun adapterOnClick(post: SMPost){

    }
}