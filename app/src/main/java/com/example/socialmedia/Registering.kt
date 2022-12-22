package com.example.socialmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser

class Registering : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registering)

        val button_Register = findViewById<Button>(R.id.button_Register)
        val editUsername = findViewById<TextView>(R.id.editUsername)
        val editEmail = findViewById<TextView>(R.id.editEmail)
        val editPassword = findViewById<TextView>(R.id.editPassword)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)

        button_Register.setOnClickListener{
            if(editUsername.text.trim().isNotEmpty() || editEmail.text.isNotEmpty() ||  editPassword.text.isNotEmpty()){

                val username:String = editUsername.text.toString().trim{ it <= ' '}
                val email:String = editEmail.text.toString().trim{ it <= ' '}
                val password:String = editPassword.text.toString().trim{ it <= ' '}

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                      OnCompleteListener<AuthResult> {task ->


                          if(task.isSuccessful){

                              val firebaseUser: FirebaseUser = task.result!!.user!!

                              Toast.makeText(
                                  this@Registering,
                                  "You are registerd successfuly.",
                                  Toast.LENGTH_SHORT
                              ).show()



                              val intent =
                                  Intent(this@Registering,MainActivity::class.java)
                              intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                              intent.putExtra("User_id",firebaseUser.uid)
                              intent.putExtra("email_id",email)
                              intent.putExtra("username_id",username)

                              startActivity(intent)
                              finish()
                          }
                          else{
                              Toast.makeText(
                                  this@Registering,
                                   task.exception!!.message.toString(),
                                  Toast.LENGTH_SHORT
                              ).show()
                          }
                      })

            }else
                Toast.makeText(this, "Please fill in all the inputs ", Toast.LENGTH_LONG).show()
        }


        tvLogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }



}
