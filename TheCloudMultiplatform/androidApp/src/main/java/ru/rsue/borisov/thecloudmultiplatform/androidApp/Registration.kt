package ru.rsue.borisov.thecloudmultiplatform.androidApp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.Accounts
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Registration : AppCompatActivity() {

    private lateinit var mLoginEditText: EditText
    private lateinit var mPhoneEditText: EditText
    private lateinit var mEmailEditText: EditText
    private lateinit var mPasswordEditText: EditText
    private lateinit var mConfPasswordEditText: EditText

    private lateinit var mRegistrationButton: Button

    private var retrofitInterface: RetrofitInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
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

        retrofitInterface = retrofit.create(RetrofitInterface::class.java)

        mLoginEditText = findViewById(R.id.editText_reg_log)

        mPhoneEditText = findViewById(R.id.editText_phone)
        mEmailEditText = findViewById(R.id.editText_email)
        mPasswordEditText = findViewById(R.id.editText_reg_password)
        mConfPasswordEditText = findViewById(R.id.editText_conf_password)

        mRegistrationButton = findViewById(R.id.button_reg_sign_up)
        mRegistrationButton.setOnClickListener {
            createPost()

        }
    }

    private fun createPost() {

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

            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Accounts>, response: Response<Accounts>) {
                if (!response.isSuccessful) {

                    val postResponse: Accounts = response.body()!!
                    var content = ""
                    content += "Code: " + response.code() + "\n"


                }

            }
        })
    }
}