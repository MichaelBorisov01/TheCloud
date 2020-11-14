package ru.rsue.borisov.thecloudmultiplatform.androidApp


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.SignatureContracts
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.internal.http.HttpMethod
//import org.apache.http.client.HttpClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rsue.borisov.thecloudmultiplatform.shared.Greeting


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private var textViewResult: TextView? = null
    private var retrofitInterface: RetrofitInterface? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewResult = findViewById(R.id.textView)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, java.util.concurrent.TimeUnit.MINUTES)
            .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
            .build()

        /*val client = HttpClient() {
            defaultRequest { // this: HttpRequestBuilder ->
                method = HttpMethod.Head
                host = "127.0.0.1"
                port = 8080
                header("X-My-Header", "MyValue")
            }
        }*/

        val gson = GsonBuilder().serializeNulls().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.0.104:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        retrofitInterface = retrofit.create(RetrofitInterface::class.java)
        //textViewResult!!.text = TheCloudController().getAll().toString()
        getPost()
    }

    private fun getPost() {

        val parameters: MutableMap<String, String> = HashMap()
        parameters["idSign"] = "1"
        parameters["_sort"] = "idSign"
        parameters["_order"] = "desc"

        val call = retrofitInterface!!.getPosts(parameters)
        call.enqueue(object : Callback<List<SignatureContracts>> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<SignatureContracts>>,
                response: Response<List<SignatureContracts>>
            ) {
                if (!response.isSuccessful) {
                    textViewResult!!.text = "Code: " + response.code()
                    return
                }
                val signatureContract = response.body()!!
                for (point in signatureContract) {
                    var content = ""
                    content += """
                               ID: ${point.idSign}
                               
                               """.trimIndent()
                    content += """
                               Date: ${point.dateSign}
                               
                               """.trimIndent()
                    content += """
                               Contract ID: ${point.idContract}
                               
                               """.trimIndent()
                    content += """
                               typeSign: ${point.typeSign}
                               
                               """.trimIndent()
                    textViewResult!!.append(content)
                }
            }

            override fun onFailure(call: Call<List<SignatureContracts>>, t: Throwable) {
                textViewResult!!.text = t.message
            }
        })
    }
}

