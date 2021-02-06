package android.exemple.com.cig_arrete

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class InterfacePrincipale : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interface__principale)
        val currentUser = auth.currentUser
        findViewById<TextView>(R.id.display_identifiant).text = currentUser!!.email
        Log.i("Users",currentUser.displayName.toString() + " " +currentUser.email.toString())

        val logout = findViewById<Button>(R.id.deconnexion)





        logout.setOnClickListener()
        {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this,"User Disconnected", Toast.LENGTH_LONG).show()
            auth.signOut()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        finish()
    }

}