package ru.rsue.borisov.thecloudmultiplatform.androidApp


//import org.apache.http.client.HttpClient

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var textViewCPU: TextView? = null
    private var textViewMemory: TextView? = null
    private var textViewHDD: TextView? = null
    private var textViewCanal: TextView? = null

    private var editTextNumberCPU: EditText? = null
    private var editTextNumberMemory: EditText? = null
    private var editTextNumberHDD: EditText? = null
    private var editTextNumberCanal: EditText? = null

    private var checkBoxExtraOptions: CheckBox? = null

    private var constraintLayoutExtraOptions: View? = null

    private lateinit var buttonAuthSignUp: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        textViewCPU = findViewById(R.id.tv_cpu)
        textViewMemory = findViewById(R.id.tv_memory)
        textViewHDD = findViewById(R.id.tv_hdd)
        textViewCanal = findViewById(R.id.tv_canal)

        editTextNumberCPU = findViewById(R.id.etnCPU)
        editTextNumberMemory = findViewById(R.id.etnMemory)
        editTextNumberHDD = findViewById(R.id.etnHDD)
        editTextNumberCanal = findViewById(R.id.etnCanal)

        checkBoxExtraOptions = findViewById(R.id.checkBox_extra_options)

        constraintLayoutExtraOptions = findViewById(R.id.Constraint_ex_options)

        buttonAuthSignUp = findViewById(R.id.button_auth_sign_up)

        /* checkBoxExtraOptions!!.setOnClickListener {
             if (checkBoxExtraOptions!!.isChecked) constraintLayoutExtraOptions!!.visibility =
                 View.VISIBLE
             else constraintLayoutExtraOptions!!.visibility = View.INVISIBLE
         }*/


        //getPost()
        //createPost()

        buttonAuthSignUp.setOnClickListener {
            val intent = Intent(this, Registration::class.java)
            startActivity(intent)
        }
    }

    /*private fun getPost() {

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
                    //textViewResult!!.text = "Code: " + response.code()
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
                    //textViewResult!!.append(content)
                }
            }*//*

            override fun onFailure(call: Call<List<SignatureContracts>>, t: Throwable) {
                //textViewResult!!.text = t.message
            }
        })
    }
*/


}

