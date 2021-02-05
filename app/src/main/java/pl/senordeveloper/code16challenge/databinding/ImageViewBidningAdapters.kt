package pl.senordeveloper.code16challenge.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import pl.senordeveloper.code16challenge.R
import pl.senordeveloper.code16challenge.User
import pl.senordeveloper.code16challenge.humanId
import timber.log.Timber

@BindingAdapter("avatar")
fun ImageView.avatar(user: User?) {
    Timber.d("avatar($humanId, $user)")
    setImageResource(
        when (user?.title) {
            "mr" -> R.drawable.ic_avatar_man
            "miss", "ms" -> R.drawable.ic_avatar_woman
            else -> R.drawable.ic_avatar
        }
    )
}