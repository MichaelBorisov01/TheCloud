package queries

class InsertQueries {

    /*fun insertRegistration(login: String, email: String, password: String, phone: String) {
        val retrofit = GraphQLInstance.graphQLService
        val paramObject = JSONObject()
        paramObject.put(
            "mutation",
            "mutation {postAccount(" +
                    "login:\"${login}\"" +
                    "email:\"${email}\"" +
                    "password:\"${password}\"" +
                    "phone:\"${phone}\")}"
        )
        GlobalScope.launch {
            try {
                val response = retrofit.insert(paramObject.toString())
                Log.e("response", response.body().toString())
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }*/
}