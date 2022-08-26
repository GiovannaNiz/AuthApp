package com.example.authapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth

        val btnLogar: Button = findViewById(R.id.btnLogar)
        btnLogar.setOnClickListener {
            performedLogar()
        }

        val txtCadastrarUsuario: TextView = findViewById(R.id.txtCadastrarUsuario)
        txtCadastrarUsuario.setOnClickListener {
            val intent = Intent( this, CadastroActivity::class.java)
            startActivity(intent)
        }

    }

    private fun performedLogar(){
        val edtLogin: EditText = findViewById(R.id.edtLogin)
        val edtSenha: EditText = findViewById(R.id.edtSenha)

        val inputLogin = edtLogin.text.toString()
        val inputSenha = edtSenha.text.toString()

        if (inputLogin.isEmpty() || inputSenha.isEmpty()){
            Toast.makeText(this, "Todos os campos precisam ser preenchidos", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(inputLogin, inputSenha)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Usuário logado com sucesso!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, RespostaActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Erro ao efetuar o login!", Toast.LENGTH_SHORT).show()
                }
            }
    }
}