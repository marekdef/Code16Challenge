package pl.senordeveloper.code16challenge

import android.os.Bundle
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class User(
    override val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val picture: String,
    val title: String
) : Serializable, HasUserId

interface HasUserId {
    val id: String
}

@JsonClass(generateAdapter = true)
data class ListResult(
    @Json(name = "data")
    val users: List<User>,
    val limit: Int,
    val offset: Int,
    val page: Int,
    val total: Int
)