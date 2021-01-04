package queries

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import ru.rsue.borisov.thecloudmultiplatform.androidApp.GraphQLInstance

  fun post() {

    //val onResponse: String? = null
    val retrofit = GraphQLInstance.graphQLService
    val paramObject = JSONObject()
    paramObject.put("query", "query {getStory(token:\"\$token\") {idStory}}")
    GlobalScope.launch {
        try {
            val response = retrofit.postDynamicQuery(paramObject.toString())
            Log.e("response", response.body().toString())
            //getText(response.body().toString())

            //retro!!.text = response.body().toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

}

