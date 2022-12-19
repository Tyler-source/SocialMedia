package com.example.socialmedia

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.io.File

class MainActivity : AppCompatActivity() {

    var storage = Firebase.storage
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager


//        // Create a storage reference from our app
//        var storageRef = storage.reference
//        // Create a child reference
//        // imagesRef now points to "images"
//        var imagesRef: StorageReference? = storageRef.child("images")
//
//        // Child references can also take paths
//        // spaceRef now points to "images/space.jpg
//        // imagesRef still points to "images"
//        var spaceRef = storageRef.child("images/space.jpg")
//
//        // parent allows us to move our reference to a parent node
//        // imagesRef now points to 'images'
//        imagesRef = spaceRef.parent
//
//        // root allows us to move all the way back to the top of our bucket
//        // rootRef now points to the root
//        val rootRef = spaceRef.root
//        // References can be chained together multiple times
//        // earthRef points to 'images/earth.jpg'
//        val earthRef = spaceRef.parent?.child("earth.jpg")
//
//        // nullRef is null, since the parent of root is null
//        val nullRef = spaceRef.root.parent
//
//        // Points to the root reference
//        storageRef = storage.reference
//
//        // Points to "images"
//        imagesRef = storageRef.child("images")
//
//        // Points to "images/space.jpg"
//        // Note that you can use variables to create child values
//        val fileName = "space.jpg"
//        spaceRef = imagesRef.child(fileName)
//
//        // File path is "images/space.jpg"
//        val path = spaceRef.path
//
//        // File name is "space.jpg"
//        val name = spaceRef.name
//
//        // Points to "images"
//        imagesRef = spaceRef.parent
//
//
//        val mountainsRef = storageRef.child("images/")
//        val mountainImagesRef = storageRef.child("images/mountains.jpg")
//        // While the file names are the same, the references point to different files
//        mountainsRef.name == mountainImagesRef.name // true
//        mountainsRef.path == mountainImagesRef.path // false
//
//
//        var imageView = findViewById<ImageView>(R.id.imageView)
//        imageView.setImageResource(R.drawable.mountains)
//
//        // Get the data from an ImageView as bytes
//
//
//        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
//        val baos = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//        val data = baos.toByteArray()
//
//        var uploadTask = mountainsRef.putBytes(data)
//        uploadTask.addOnFailureListener {
//            println("Failed Upload")
//        }.addOnSuccessListener { taskSnapshot ->
//            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
//            // ...
//            println("Success Upload")
//        }

// Register observers to listen for when the download is done or if it failed
        println("This is a new comment")
    }
}