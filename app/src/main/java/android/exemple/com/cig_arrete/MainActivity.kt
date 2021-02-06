package android.exemple.com.cig_arrete

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val connexion = findViewById<Button>(R.id.connexion)
        val enregistrer = findViewById<Button>(R.id.enregistrer)

        connexion.setOnClickListener()
        {
            val intent = Intent(this, Connexion::class.java)
            startActivity(intent)
        }
        enregistrer.setOnClickListener()
        {
            val intent = Intent(this, Enregistrer::class.java)
            startActivity(intent)
        }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            Toast.makeText(this,"User connected", Toast.LENGTH_LONG).show()
            val intent = Intent(this, InterfacePrincipale::class.java)
            startActivity(intent)
        }

}}