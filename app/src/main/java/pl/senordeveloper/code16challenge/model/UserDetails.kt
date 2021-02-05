package pl.senordeveloper.code16challenge.model

import com.squareup.moshi.JsonClass
import pl.senordeveloper.code16challenge.HasUserId
import pl.senordeveloper.code16challenge.User
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class UserDetails(
    override val id: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val gender: String,
    val location: Location,
    val picture: String,
    val registerDate: String,
    val dateOfBirth: String
) : Serializable, HasUserId {
    fun toUser() = User(
        id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        picture = picture,
        title = title
    )
}