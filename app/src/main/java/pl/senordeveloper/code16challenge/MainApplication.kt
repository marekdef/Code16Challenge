package pl.senordeveloper.code16challenge

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton
import pl.senordeveloper.code16challenge.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

class MainApplication : Application(), DIAware {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override val di: DI by DI.lazy {
        bind<MainViewModel>() with provider {
            MainViewModel(this@MainApplication)
        }

        bind<DetailsViewModel>() with provider {
            DetailsViewModel(this@MainApplication)
        }

        bind<UserRepository>() with singleton {
            RemoteUsersRepository(di)
        }
        bind<UserService>() with singleton {
            provideUserService(retrofit = instance())
        }

        bind<Retrofit>() with singleton {
            provideRetrofit(instance(), instance())
        }

        bind<OkHttpClient>() with singleton {
            provideClient()
        }

        bind<Moshi>() with singleton {
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }
    }

    private fun provideClient() = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    ).build()

    private fun provideRetrofit(client: OkHttpClient, moshi: Moshi) = Retrofit.Builder()
        .baseUrl("https://dummyapi.io")
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private fun provideUserService(retrofit: Retrofit) = retrofit.create(UserService::class.java)
}