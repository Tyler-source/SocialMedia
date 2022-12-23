package com.example.socialmedia

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayInputStream
import kotlin.concurrent.thread

val MAX_SIZE: Long = 1024 * 1024

class MainActivity : AppCompatActivity() {

    var storage = Firebase.storage
    private lateinit var recyclerView: RecyclerView
    lateinit var dataset: ArrayList<SMPost>
    //var user = intent.getStringExtra("email_id")
    //var userID = intent.getStringExtra("User_id")


    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var textView:TextView = findViewById(R.id.username)
        //textView.text = "Logged in as: $user"

        val refreshBtn = findViewById<Button>(R.id.refreshBtn)
        val logoutBtn = findViewById<Button>(R.id.logoutbtn)
        val newPostBtn = findViewById<Button>(R.id.newPostBtn)

//        var drw = resources.getDrawable(R.drawable.mountains)
//
//        var post1 : SMPost = SMPost("0","0",drw)
        //drw = resources.getDrawable(R.drawable.rivers)
        //var post2 : SMPost = SMPost(1,1,drw)
        //drw = resources.getDrawable(R.drawable.rivers_foreground)
        //var post3 : SMPost = SMPost(2,2,drw)

        dataset = arrayListOf()


        val adapter = SMPostListAdapter(dataset,this,this::adapterOnClick)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager

        refresh()
        //recyclerView = findViewById(R.id.recyclerView)
        //val manager = LinearLayoutManager(this)
        //recyclerView.layoutManager = manager




        refreshBtn.setOnClickListener {
            refresh()
        }
        logoutBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        newPostBtn.setOnClickListener {
            val intent = Intent(this, Publication::class.java)
            //intent.putExtra("username" , userID)
            startActivity(intent)
        }

// Register observers to listen for when the download is done or if it failed
        println("This is a new comment")
    }
    private fun adapterOnClick(post: SMPost){

    }


    private fun refresh(){
        dataset.clear()
        val listRef = storage.reference.child("images")
        listRef.listAll()
            .addOnSuccessListener { (items, prefixes) ->

                items.forEach { item ->
                    // All the items under listRef.
                    item.metadata.addOnSuccessListener { metadata ->
                        //println(item.name)
                        item.getBytes(MAX_SIZE).addOnCompleteListener{ input ->
                            //println("UID: "+metadata.getCustomMetadata("UID").toString())
                            val bais = ByteArrayInputStream(input.result)
                            val drw = Drawable.createFromStream(bais, "articleImage")
                            var post = SMPost(metadata.getCustomMetadata("PostTitle")!!,item.path, metadata.getCustomMetadata("UID"),drw)
                            dataset.add(post)
                            val adapter = SMPostListAdapter(dataset,this,this::adapterOnClick)
                            recyclerView = findViewById(R.id.recyclerView)
                            recyclerView.adapter = adapter
                            val manager = LinearLayoutManager(this)
                            recyclerView.layoutManager = manager

                        }
                    }.addOnFailureListener{
                        println("Error Accessing Metadata of " + item.name)
                    }
                }
            }
            .addOnFailureListener {
                // Uh-oh, an error occurred!
            }


    }
}