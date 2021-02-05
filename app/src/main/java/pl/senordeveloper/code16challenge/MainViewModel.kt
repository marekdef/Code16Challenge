package pl.senordeveloper.code16challenge

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance

class MainViewModel(
    private val context: Context,
) : AndroidViewModel(
    context.applicationContext as Application
), DIAware {
    override val di: DI by di()

    private val userRepository by di.instance<UserRepository>()

    private val usersResource = userRepository.list()

    val users = usersResource
        .map {
            it as? Result.Success<List<User>>
        }.filterNotNull().map { it.result }.asLiveData()
}