package android.exemple.com.cig_arrete

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
//todo connexion / passage prochaine page
class Connexion : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)
    }

    fun connectAccount(view: View){
        val password = findViewById<EditText>(R.id.password).text.toString()
        val identifiant = findViewById<EditText>(R.id.Id).text.toString()
        signin(identifiant,password)
    }

    private fun signin(email : String,password : String)
    {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, InterfacePrincipale::class.java)
                    startActivity(intent)
                    Toast.makeText(this,"User connected",Toast.LENGTH_LONG).show()
                    finish()
                }
                else
                {
                    Toast.makeText(this,"Mauvais Mdp",Toast.LENGTH_LONG).show()
                }

            }
    }

}