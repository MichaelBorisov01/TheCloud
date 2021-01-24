package ru.rsue.borisov.thecloudmultiplatform.androidApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var mLoginEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var buttonLogIn: Button
    private lateinit var buttonSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        mLoginEditText = findViewById(R.id.auth_login)
        mPasswordEditText = findViewById(R.id.auth_password)
        buttonLogIn = findViewById(R.id.button_log_in)
        buttonSignUp = findViewById(R.id.button_auth_sign_up)

        buttonSignUp.setOnClickListener {
            getLogin(mLoginEditText.text.toString(), mPasswordEditText.text.toString())


            if (getLogin(
                    mLoginEditText.text.toString(),
                    mPasswordEditText.text.toString()
                ).isNotEmpty()
            ) {
                Toast.makeText(this, "Здравствуйте", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Убедитесь в корректности данных или зарегистрируйтесь",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getLogin(login: String, password: String): String {
        val retrofit = GraphQLInstance.graphQLService
        val paramObject = JSONObject()
        paramObject.put("query", "query {getLogin(login:\"$login\",password:\"$password\")}")
        /*GlobalScope.launch {
            try {
                val response = retrofit.getLog(paramObject.toString())

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/

        return paramObject.toString()
    }
}




