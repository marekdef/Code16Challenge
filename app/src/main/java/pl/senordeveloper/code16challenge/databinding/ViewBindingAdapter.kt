package pl.senordeveloper.code16challenge.databinding

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.databinding.BindingAdapter
import pl.senordeveloper.code16challenge.humanId
import timber.log.Timber

@BindingAdapter("shortToast")
fun View.shortToast(message: String?) {
    Timber.d("shortToast($humanId, $message)")
    if (message == null)
        return

    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@BindingAdapter("visibleGone")
fun View.visibleGone(visible: Boolean) {
    Timber.d("visibleGone($humanId, $visible)")

    visibility = if (visible) VISIBLE else GONE
}