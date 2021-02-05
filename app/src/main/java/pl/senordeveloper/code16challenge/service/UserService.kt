package pl.senordeveloper.code16challenge.service

import pl.senordeveloper.code16challenge.BuildConfig
import pl.senordeveloper.code16challenge.ListResult
import pl.senordeveloper.code16challenge.User
import pl.senordeveloper.code16challenge.model.UserDetails
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserService {
    @Headers("app-id: ${BuildConfig.DummyApiAppId}")
    @GET("/data/api/user")
    suspend fun list(): ListResult

    @Headers("app-id: ${BuildConfig.DummyApiAppId}")
    @GET("/data/api/user/{userId}")
    suspend fun details(@Path("userId") userId: String): UserDetails
}