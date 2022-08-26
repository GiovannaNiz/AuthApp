package com.example.authapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        auth = Firebase.auth

        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {
            performedCadastro()
        }
    }

    private fun performedCadastro(){
        val edtLogin: EditText = findViewById(R.id.edtLogin)
        val edtSenha: EditText = findViewById(R.id.edtSenha)

        val inputLogin = edtLogin.text.toString()
        val inputSenha = edtSenha.text.toString()

        if (inputLogin.isEmpty() || inputSenha.isEmpty()){
            Toast.makeText(this, "Todos os campos precisam ser preenchidos!", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(inputLogin, inputSenha)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "Erro ao realizar cadastro!", Toast.LENGTH_SHORT).show()

                }
            }
    }
}