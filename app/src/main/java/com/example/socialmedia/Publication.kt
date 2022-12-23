package com.example.socialmedia

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.math.abs

class Publication : AppCompatActivity() {


    var pickedPhoto : Uri? = null
    var pickedBitMap : Bitmap? = null
    var storage = Firebase.storage
    lateinit var postTitle :TextView

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publication)

        val firebaseImage = findViewById<ImageView>(R.id.firebaseImage)
        postTitle = findViewById<TextView>(R.id.txtData)
        val uploadImageBtn = findViewById<Button>(R.id.uploadImageBtn)
        val edBack = findViewById<TextView>(R.id.edBack)


         uploadImageBtn.setOnClickListener(){
        uploadImage(firebaseImage.drawable.toBitmap())
             Toast.makeText(this,"Uploaded",Toast.LENGTH_LONG).show()
        }


        edBack.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent) }
    }

    fun pickedPhoto(view:View){

        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            1)
        }else{
            val galleryIntext = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntext,2)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val galleryIntext = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntext,2)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val firebaseImage = findViewById<ImageView>(R.id.firebaseImage)
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            pickedPhoto = data.data
            if(SDK_INT >= 28){
                val source = ImageDecoder.createSource(this.contentResolver, pickedPhoto!!)
                pickedBitMap = ImageDecoder.decodeBitmap(source)
                firebaseImage.setImageBitmap(pickedBitMap)
            }else{
                pickedBitMap = MediaStore.Images.Media.getBitmap(this.contentResolver,pickedPhoto)
                firebaseImage.setImageBitmap(pickedBitMap)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }




    @RequiresApi(Build.VERSION_CODES.N)
    private fun uploadImage(Image:Bitmap) {
        var storageRef : StorageReference = storage.reference

        var rand: Random = Random()
        rand.ints(0,100000)
        var Post_Id = abs(rand.nextInt())
        println(Post_Id)
        var postRef = storageRef.child("images/$Post_Id.jpg")

        val metadata = storageMetadata {
            contentType = "image/jpg"
            setCustomMetadata("UID", "input")
            setCustomMetadata("PostTitle", postTitle.text.toString() )
        }


        val stream = ByteArrayOutputStream()
        Image.compress(Bitmap.CompressFormat.JPEG, 100, stream)

        var uploadTask = postRef.putBytes(stream.toByteArray())
        uploadTask.addOnSuccessListener { println("done")
            postRef.metadata.addOnSuccessListener { metadata -> }
            postRef.updateMetadata(metadata).addOnSuccessListener {  println("metadata working")}.addOnFailureListener{ println("metadata failed")}
        }.addOnFailureListener{println("not done")}



    }

}





