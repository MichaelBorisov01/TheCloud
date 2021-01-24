package ru.rsue.borisov.thecloudmultiplatform.androidApp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity() {

    private lateinit var mLoginEditText: EditText
    private lateinit var mPhoneEditText: EditText
    private lateinit var mEmailEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var mConfPasswordEditText: EditText
    private lateinit var mRegistrationButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        mLoginEditText = findViewById(R.id.reg_log)
        mPhoneEditText = findViewById(R.id.reg_phone)
        mEmailEditText = findViewById(R.id.reg_email)
        mPasswordEditText = findViewById(R.id.reg_password)
        mConfPasswordEditText = findViewById(R.id.reg_conf_password)
        mRegistrationButton = findViewById(R.id.button_reg_sign_up)

        mRegistrationButton.setOnClickListener {
            if (mPasswordEditText.text.toString().length > 5 &&
                mLoginEditText.text.toString().length > 4 && mPhoneEditText.text.toString().length == 11 &&
                mEmailEditText.text.toString().length > 10 &&
                mPasswordEditText.text.toString() == mConfPasswordEditText.text.toString()
            ) {
                Toast.makeText(this, "Регистрация...", Toast.LENGTH_SHORT).show()

                insertRegistration(
                    mLoginEditText.text.toString(),
                    mEmailEditText.text.toString(),
                    mPasswordEditText.text.toString(),
                    mPhoneEditText.text.toString()
                )

                val intent = Intent(this, OptionsActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(
                    this,
                    "Убедитесь в корректности данных.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun insertRegistration(login: String, email: String, password: String, phone: String) {
        val retrofit = GraphQLInstance.graphQLService
        val paramObject = JSONObject()
        paramObject.put(
            "query",
            "mutation {postAccount(login:\"$login\",email:\"$email\",password:\"$password\",phone:\"$phone\")}"
        )
        /*GlobalScope.launch {
            try {
                val response = retrofit.insertReg(paramObject.toString())
                //Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }*/
    }
}