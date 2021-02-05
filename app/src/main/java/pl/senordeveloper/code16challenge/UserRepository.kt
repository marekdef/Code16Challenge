package pl.senordeveloper.code16challenge

import kotlinx.coroutines.flow.Flow
import pl.senordeveloper.code16challenge.model.UserDetails

interface UserRepository {
    fun list() : Flow<Result<List<User>>>

    fun details(userId: HasUserId) : Flow<Result<UserDetails>>
}