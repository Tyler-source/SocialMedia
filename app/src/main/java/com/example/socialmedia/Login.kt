package com.example.socialmedia

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val Button_Login = findViewById<Button>(R.id.button_login)
        val edEmail = findViewById<TextView>(R.id.edEmail)
        val edPassword = findViewById<TextView>(R.id.edPassword)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        Button_Login.setOnClickListener{
            if(edEmail.text.trim().isNotEmpty() || edPassword.text.trim().isNotEmpty()){


                val email:String = edEmail.text.toString().trim{ it <= ' '}
                val password:String = edPassword.text.toString().trim{ it <= ' '}

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task ->


                            if(task.isSuccessful){

                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@Login,
                                    "You are registerd successfuly.",
                                    Toast.LENGTH_SHORT
                                ).show()



                                val intent =
                                    Intent(this@Login,MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("User_id",
                                    FirebaseAuth.getInstance().currentUser!!.uid
                                )
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                Toast.makeText(
                                    this@Login,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        })
            }
            else
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_LONG).show()
        }
        tvRegister.setOnClickListener {
            val intent = Intent(this,Registering::class.java)
            startActivity(intent)
        }
    }


}
