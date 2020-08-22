package com.e.progress

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class Register: AppCompatActivity(){

    private lateinit var db: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth
    lateinit var ETname: EditText
    lateinit var ETsurname: EditText
    lateinit var ETemail: EditText
    lateinit var reference: CollectionReference
    lateinit var ETpassword:EditText
    lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.registration)

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        ETname = findViewById(R.id.et_name)
        ETsurname = findViewById(R.id.et_surname)
        ETemail = findViewById(R.id.et_reg_email)
        ETpassword = findViewById(R.id.et_reg_password)
        buttonRegister = findViewById(R.id.button_register)

        buttonRegister.setOnClickListener(View.OnClickListener {
            onClick(){

            }
        })
    }

    fun createAccount(name: String,surname: String, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Toast.makeText(this,"Тіркеу сәтті аяқталды", Toast.LENGTH_LONG).show()
                val intent = Intent(this@Register, MainActivity::class.java)
                intent.putExtra("key","registration")
                startActivity(intent)
            }else{
                Toast.makeText(this,"Сіз енгізген ақпарат бойынша аккаунт бар", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onClick(view: () -> Unit){

    }

    fun startRegistration(){
        val name: String = ETname.text.toString().trim()
        val surname: String = ETsurname.text.toString().trim()
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
        else if(surname.isEmpty()){
            Toast.makeText(this, "Тегіңізді енгізіңіз", Toast.LENGTH_SHORT).show()
        }

        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(this){


            }.addOnFailureListener(this){
                Toast.makeText(this,"Unable to register user!", Toast.LENGTH_SHORT).show()
                it.printStackTrace()
            }
        }
    }
}
