import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import okhttp3.Credentials
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

interface Api {

    companion object {
        fun create(user: String, token: String): Api =
            retrofit(okHttp(user, token)).create()
    }

    @GET("/notifications")
    suspend fun getNotifications(): List<JsonObject>
}

private fun okHttp(user: String, token: String) = OkHttpClient
    .Builder()
    .addInterceptor { chain ->
        val credentials = Credentials.basic(user, token)
        val request = chain
            .request()
            .newBuilder()
            .addHeader("Authorization", credentials)
            .build()
        chain.proceed(request)
    }
    .build()

private fun jsonConverter() =
    Json.asConverterFactory("application/json".toMediaType())

private fun retrofit(client: OkHttpClient) =
    Retrofit
        .Builder()
        .baseUrl("https://api.github.com")
        .client(client)
        .addConverterFactory(jsonConverter())
        .build()