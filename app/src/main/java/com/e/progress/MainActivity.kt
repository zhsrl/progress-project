package com.e.progress

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var ETemail: EditText
    lateinit var ETpassword: EditText
    lateinit var signIn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        ETemail = findViewById(R.id.et_email)
        ETpassword = findViewById(R.id.et_password)

        signIn = findViewById(R.id.button_sign_in)

    }



    fun updateUI(account: FirebaseUser?) {
        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ProfileInfoActivity::class.java))
        } else {
            Toast.makeText(this, "U Didnt signed in", Toast.LENGTH_LONG).show()
        }
    }

    override fun onStart() {
        super.onStart()

        var currentUser: FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    fun onClick(view: View){
        signIn(ETemail.text.toString(), ETpassword.text.toString())
    }

    fun signIn(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Authorization successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this@MainActivity, ProfileInfoActivity::class.java)
                intent.putExtra("key","ProfileInfo")
                startActivity(intent)
            }else{
                Toast.makeText(this, "Authorization failed", Toast.LENGTH_LONG).show()
            }
        }
    }


}