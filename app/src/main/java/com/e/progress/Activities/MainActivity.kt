package com.e.progress.Activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.e.progress.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var ETemail: EditText
    lateinit var ETpassword: EditText
    lateinit var signIn: Button
    lateinit var registerAccount: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var constraintLayout: ConstraintLayout = findViewById(R.id.main_layout)
        var animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        mAuth = FirebaseAuth.getInstance()

        ETemail = findViewById(R.id.et_email)
        ETpassword = findViewById(R.id.et_password)

        signIn = findViewById(R.id.button_sign_in)
        signIn.setOnClickListener(){
            startSignIn()
        }

        registerAccount = findViewById(R.id.tv_registration)
        registerAccount.setOnClickListener(){
            val intent = Intent(applicationContext, SignUp::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

    }



    fun updateUI(account: FirebaseUser?) {
        if (account != null) {
            Toast.makeText(this, "U Signed In successfully", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ProfileMainActivity::class.java))

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
                val intent = Intent(this@MainActivity, ProfileMainActivity::class.java)
                intent.putExtra("key","ProfileInfo")
                startActivity(intent)
            }else{
                Toast.makeText(this, "Authorization failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun startSignIn(){
        val email: String = ETemail.text.toString().trim()
        val password: String = ETpassword.text.toString().trim()

        if(email.isEmpty()){
            Toast.makeText(this, "Почтаңызды енгізіңіз", Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Құпия сөзді енгізіңіз", Toast.LENGTH_SHORT).show()
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                    task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Авторизация сәтті өтті", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@MainActivity, ProfileMainActivity::class.java)
                    intent.putExtra("username","enterusername")
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Почта немесе құпия сөз дұрыс енгізілмеді", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}