package ru.rsue.borisov.thecloudmultiplatform.androidApp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class Registration : AppCompatActivity() {


    private lateinit var mLoginEditText: EditText
    private lateinit var mPhoneEditText: EditText
    private lateinit var mEmailEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var mConfPasswordEditText: EditText
    private lateinit var mRegistrationButton: Button

    //private lateinit var insert: InsertQueries
    //private var retrofitInterface: RetrofitInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        /* val okHttpClient: OkHttpClient = OkHttpClient.Builder()
             .connectTimeout(1, java.util.concurrent.TimeUnit.MINUTES)
             .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
             .writeTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
             .build()

         val gson = GsonBuilder().serializeNulls().create()
         val retrofit = Retrofit.Builder()
             .baseUrl("http://192.168.0.104:8080/")
             .client(okHttpClient)
             .addConverterFactory(GsonConverterFactory.create(gson))
             .build()

         retrofitInterface = retrofit.create(RetrofitInterface::class.java)*/

        mLoginEditText = findViewById(R.id.reg_log)
        mPhoneEditText = findViewById(R.id.reg_phone)
        mEmailEditText = findViewById(R.id.reg_email)
        mPasswordEditText = findViewById(R.id.reg_password)
        mConfPasswordEditText = findViewById(R.id.reg_conf_password)
        mRegistrationButton = findViewById(R.id.button_reg_sign_up)

        mRegistrationButton.setOnClickListener {
            if (mPasswordEditText.text.toString() == mConfPasswordEditText.text.toString()) {
                Toast.makeText(this , "button", Toast.LENGTH_SHORT).show()
                insertRegistration(
                    mLoginEditText.text.toString(),
                    mEmailEditText.text.toString(),
                    mPasswordEditText.text.toString(),
                    mPhoneEditText.text.toString()
                )
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
        GlobalScope.launch {
            try {
                val response = retrofit.insert(paramObject.toString())
                Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
    /* private fun createPost() {

         val registration = Accounts(
             null,
             mLoginEditText.text.toString(),
             mPhoneEditText.text.toString(),
             mEmailEditText.text.toString(),
             mPasswordEditText.text.toString()
         )


         val call: Call<Accounts> = retrofitInterface!!.createPost(registration)

         call.enqueue(object : Callback<Accounts> {
             override fun onFailure(call: Call<Accounts>, t: Throwable) {
                 Toast.makeText(applicationContext, t.toString(), Toast.LENGTH_SHORT).show()
             }

             @SuppressLint("SetTextI18n")
             override fun onResponse(call: Call<Accounts>, response: Response<Accounts>) {
                 if (!response.isSuccessful) {
                     Toast.makeText(applicationContext, response.code(), Toast.LENGTH_SHORT).show()
                 }

             }
         })
     }*/
}