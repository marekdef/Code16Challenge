package pl.senordeveloper.code16challenge

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import pl.senordeveloper.code16challenge.model.UserDetails
import pl.senordeveloper.code16challenge.service.UserService
import timber.log.Timber

class RemoteUsersRepository(
    override val di: DI
) : UserRepository,
    DIAware {
    private val userService by di.instance<UserService>()

    override fun list(): Flow<Result<List<User>>> = flow {
        emit(Result.Loading())

        try {
            emit(Result.Success(userService.list().users))
        } catch (throwable: Throwable) {
            emit(Result.Error<List<User>>(throwable))
        }
    }

    override fun details(userId: HasUserId): Flow<Result<UserDetails>> = flow {
        Timber.d("details($userId)")
        emit(Result.Loading())

        try {
            emit(Result.Success(userService.details(userId.id)))
        } catch (throwable: Throwable) {
            emit(Result.Error<UserDetails>(throwable))
        }
    }
}
