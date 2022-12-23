package com.example.socialmedia

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.component1
import com.google.firebase.storage.ktx.component2
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayInputStream

val MAX_SIZE: Long = 1024 * 1024

class MainActivity : AppCompatActivity() {

    var storage = Firebase.storage
    private lateinit var recyclerView: RecyclerView
    lateinit var dataset: ArrayList<SMPost>


    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var drw = resources.getDrawable(R.drawable.mountains)

        //var post1 : SMPost = SMPost(0,0,drw)
        drw = resources.getDrawable(R.drawable.rivers)
        //var post2 : SMPost = SMPost(1,1,drw)
        drw = resources.getDrawable(R.drawable.rivers_foreground)
        //var post3 : SMPost = SMPost(2,2,drw)

        //dataset = arrayListOf(post1,post2,post3)
        dataset = refresh()
        val adapter = SMPostListAdapter(dataset,this,this::adapterOnClick)

        //recyclerView = findViewById(R.id.recyclerView)
        //val manager = LinearLayoutManager(this)
        //recyclerView.layoutManager = manager

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager




// Register observers to listen for when the download is done or if it failed
        println("This is a new comment")
    }
    private fun adapterOnClick(post: SMPost){

    }
    private fun refresh(): ArrayList<SMPost> {
        var newDataSet: ArrayList<SMPost>
        newDataSet = arrayListOf()
        val listRef = storage.reference.child("images")
        listRef.listAll()
            .addOnSuccessListener { (items, prefixes) ->

                items.forEach { item ->
                    // All the items under listRef.
                    item.metadata.addOnSuccessListener { metadata ->
                        item.getBytes(MAX_SIZE).addOnCompleteListener{ input ->
                            val bais = ByteArrayInputStream(input.result)
                            val drw = Drawable.createFromStream(bais, "articleImage")
                            newDataSet.add(SMPost(item.name, metadata.getCustomMetadata("UID"),drw))
                        }
                    }.addOnFailureListener{
                        println("Error Accessing Metadata of " + item.name)
                    }
                }
            }
            .addOnFailureListener {
                // Uh-oh, an error occurred!
            }
        return newDataSet
    }
}