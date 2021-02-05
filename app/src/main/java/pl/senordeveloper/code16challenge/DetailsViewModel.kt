package pl.senordeveloper.code16challenge

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.distinctUntilChanged
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.snakydesign.livedataextensions.filter
import com.snakydesign.livedataextensions.merge
import com.snakydesign.livedataextensions.nonNull
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import org.kodein.di.instance
import pl.senordeveloper.code16challenge.model.UserDetails

class DetailsViewModel(
    context: Context
) : AndroidViewModel(context.applicationContext as Application),
    DIAware {
    override val di: DI by di()

    private val userRepository by di.instance<UserRepository>()

    private val _user = MutableLiveData<User>()

    private val userDetailsResult = _user.distinctUntilChanged().switchMap {
        userRepository.details(it).asLiveData()
    }

    val userDetails = userDetailsResult.map {
        it as? Result.Success<UserDetails>
    }.nonNull().map {
        it as Result.Success<UserDetails>
    }.map {
        it.result
    }

    val userDetailsError = userDetailsResult.map {
        it as? Result.Error
    }.map {
        it?.throwable?.message
    }

    val isLoading = userDetailsResult.map {
        it is Result.Loading
    }

    val user = merge(listOf(
        _user.distinctUntilChanged(),
        userDetails.map { userDetails ->
            userDetails.toUser()
        }
    ))


    fun newUser(user: User) {
        _user.value = user
    }
}