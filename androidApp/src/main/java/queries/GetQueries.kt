package queries

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import ru.rsue.borisov.thecloudmultiplatform.androidApp.GraphQLInstance
import ru.rsue.borisov.thecloudmultiplatform.androidApp.GraphQLInstance.token

fun post() {
    val retrofit = GraphQLInstance.graphQLService
    val paramObject = JSONObject()
    paramObject.put("query", "query {getStory(token:\"$token\") {idStory}}")
    GlobalScope.launch {
        try {
            //val response = retrofit.post(paramObject.toString())
            //Log.e("response", response.body().toString())
            //retro!!.text = response.body().toString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}

