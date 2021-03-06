package com.e.progress.Activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.e.progress.Fragments.HomeFragment
import com.e.progress.R
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class SignUp : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth
    lateinit var ETname: EditText
    lateinit var ETsurname: EditText
    lateinit var ETemail: EditText
    lateinit var reference: CollectionReference
    lateinit var ETpassword: EditText
    lateinit var buttonRegister: Button
    lateinit var signIn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var constraintLayout: ConstraintLayout = findViewById(R.id.second_main_layout)
        var animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(4000)
        animationDrawable.start()

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        ETname = findViewById(R.id.et_name)
        ETemail = findViewById(R.id.et_reg_email)
        ETpassword = findViewById(R.id.et_reg_password)
        buttonRegister = findViewById(R.id.button_register)

        buttonRegister.setOnClickListener(View.OnClickListener {
            startRegistration()
        })

        signIn = findViewById(R.id.tv_reg_page_registration)

        signIn.setOnClickListener(View.OnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        })
    }

    fun onClick(view: () -> Unit){

    }



    fun startRegistration(){

        val name: String = ETname.text.toString().trim()
        val email: String = ETemail.text.toString().trim()
        val password: String = ETpassword.text.toString().trim()

        if(email.isEmpty()){
            Toast.makeText(this,"Почтаңызды енгізіңіз", Toast.LENGTH_SHORT).show()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this,"Дұрыс почтаны енгізіңіз", Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty()){
            Toast.makeText(this,"Құпия сөз енгізіңіз", Toast.LENGTH_SHORT).show()
        }
        else if(password.length < 6){
            Toast.makeText(this, "Құпия сөз 6 белгіден кем болмауы тиіс", Toast.LENGTH_SHORT).show()
        }
        else if(name.isEmpty()){
            Toast.makeText(this, "Есіміңізді енгізіңіз", Toast.LENGTH_SHORT).show()
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                fun onSuccess(authResult: AuthResult){
                    var user: FirebaseUser? = authResult.user
                    val dataMap: MutableMap<String, String> =
                        HashMap()
                    dataMap["name"] = name
                    reference.add(dataMap)
                    intent.putExtra("name", name)
                    reference.add(dataMap).addOnSuccessListener {
                        Toast.makeText(this,"Тіркеу сәтті аяқталды", Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    }.addOnFailureListener{
                        Toast.makeText(this, "User registered but unable to save name and surname!", Toast.LENGTH_SHORT).show()
                        it.printStackTrace()
                    }

                }
            }.addOnFailureListener(this){
                Toast.makeText(this,"Сіз терген ақпарат бойынша аккаунт тіркелген!", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            }

        }



    }
}