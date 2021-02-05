package pl.senordeveloper.code16challenge

import android.content.Context
import androidx.lifecycle.Observer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.Extensions
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import pl.senordeveloper.code16challenge.model.Location
import pl.senordeveloper.code16challenge.model.UserDetails
import java.util.UUID

@Extensions(

    ExtendWith(InstantExecutorExtension::class),
    ExtendWith(CoroutinesTestExtension::class),
)
internal class DetailsViewModelTest {
    private val userRepository = mockk<UserRepository>()

    private val context = mockk<Context> {
        every {
            applicationContext
        } returns
            mockk<MainApplication>(relaxed = true) {
                every {
                    di
                } returns DI.lazy {
                    bind<UserRepository>() with singleton {
                        userRepository
                    }
                }
            }
    }

    @Test
    fun `should observe user from the passed user`() {
        val detailsViewModel = DetailsViewModel(context)
        val user = stubUser()
        val userObserver = mockk<Observer<in User>>(relaxed = true)
        detailsViewModel.user.observeForever(
            userObserver
        )
        every {
            userRepository.details(user)
        } returns flowOf()

        detailsViewModel.newUser(user)

        verify(exactly = 1) {
            userObserver.onChanged(user)
        }
    }

    @Test
    fun `should observe user from the user details`() {
        val detailsViewModel = DetailsViewModel(context)
        val userDetails = stubUserDetails()
        val user = stubUser(id = userDetails.id)

        val userObserver = mockk<Observer<in User>>(relaxed = true)
        detailsViewModel.user.observeForever(
            userObserver
        )
        every {
            userRepository.details(user)
        } returns flowOf(Result.Success(userDetails))

        detailsViewModel.newUser(user)

        verify(exactly = 1) {
            userObserver.onChanged(user)
            userObserver.onChanged(userDetails.toUser())
        }
    }

    private fun stubUser(
        id: String = UUID.randomUUID().toString(),
        email: String = UUID.randomUUID().toString(),
        firstName: String = UUID.randomUUID().toString(),
        lastName: String = UUID.randomUUID().toString(),
        picture: String = UUID.randomUUID().toString(),
        title: String = UUID.randomUUID().toString()
    ) = User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        picture = picture,
        title = title,
    )

    private fun stubUserDetails(
        id: String = UUID.randomUUID().toString(),
        email: String = UUID.randomUUID().toString(),
        firstName: String = UUID.randomUUID().toString(),
        lastName: String = UUID.randomUUID().toString(),
        picture: String = UUID.randomUUID().toString(),
        title: String = UUID.randomUUID().toString(),
        dateOfBirth: String = UUID.randomUUID().toString(),
        gender: String = UUID.randomUUID().toString(),
        phone: String = UUID.randomUUID().toString(),
        registerDate: String = UUID.randomUUID().toString(),
        location: Location = stubLocation()

    ) = UserDetails(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        picture = picture,
        title = title,
        dateOfBirth = dateOfBirth,
        gender = gender,
        location = location,
        phone = phone,
        registerDate = registerDate
    )

    private fun stubLocation(
        city: String = UUID.randomUUID().toString(),
        country: String = UUID.randomUUID().toString(),
        state: String = UUID.randomUUID().toString(),
        street: String = UUID.randomUUID().toString(),
        timezone: String = UUID.randomUUID().toString(),
    ): Location = Location(
        city = city,
        country = country,
        state = state,
        street = street,
        timezone = timezone
    )
}