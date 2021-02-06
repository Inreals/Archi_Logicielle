package android.exemple.com.cig_arrete

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//todo passer à la prochaine page
class Enregistrer : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enregistrer)
    }


    fun createAccount(view: View) {
        val email = findViewById<TextView>(R.id.mail).text.toString()
        val password = findViewById<TextView>(R.id.Mdp_r).text.toString()
        Log.d("I", "createAccount:$email")
        if (!validateForm()) {
            Toast.makeText(baseContext, "Veuillez remplir tous les champs et vérifier que les deux mots de passes concordes.",
                    Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("I", "createUserWithEmail:success")
                    Toast.makeText(baseContext, "Compte crée,veuillez vous connecter.",
                        Toast.LENGTH_SHORT).show()

                    val datePicker = findViewById<DatePicker>(R.id.naissance)
                    val date =  datePicker.year.toString() + "-"+(datePicker.month+1).toString() + "-" + datePicker.dayOfMonth.toString()

                    val user = User(findViewById<TextView>(R.id.Id_r).text.toString(),date)
                    val database = Firebase.database
                    val path = database.getReference("enregistrer").child(auth.currentUser!!.uid)
                    path.setValue(user)
                    auth.signOut()



                    val intent = Intent(this, Connexion::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Log.w("I", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = findViewById<EditText>(R.id.mail).text
        if (TextUtils.isEmpty(email)) valid = false
        val identifiant = findViewById<EditText>(R.id.Id_r).text
        if (TextUtils.isEmpty(identifiant)) valid = false
        val password = findViewById<EditText>(R.id.Mdp_r).text
        if (TextUtils.isEmpty(password)) valid = false
        val passwordverif = findViewById<EditText>(R.id.Mdpverif).text
        if (TextUtils.isEmpty(passwordverif)) valid = false

        if (!password.toString().equals(passwordverif.toString())) valid = false


        return valid
    }

}
