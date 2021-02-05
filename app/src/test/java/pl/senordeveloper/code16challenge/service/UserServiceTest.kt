package pl.senordeveloper.code16challenge.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Logger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal class UserServiceTest {

    private val logger = object : Logger {
        override fun log(message: String) {
            println(message)
        }
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor(logger).apply {
                level = BODY
            }
        ).build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://dummyapi.io/data/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val userService = retrofit.create(UserService::class.java)

    @Test
    fun `list should not fail`() {
        assertDoesNotThrow {
            runBlocking {
                userService.list()
            }
        }
    }

    @Test
    fun `details should not fail`() {
        assertDoesNotThrow {
            runBlocking {
                userService.details("0F8JIqi4zwvb77FGz6Wt")
            }
        }
    }
}