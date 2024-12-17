package contas.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class Account (val description: String? = null )
class ServerAPI {
    suspend fun BuscarContas(): Flow<MutableList<Account>> {
        val client = HttpClient{
            install(ContentNegotiation){
                json(
                    Json {
                    ignoreUnknownKeys = true
                })
            }
        }
        val accounts = client.get("http://192.168.1.24:84/api/Account").body<MutableList<Account>>()
        return MutableStateFlow(accounts)
    }
}