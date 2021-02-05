package pl.senordeveloper.code16challenge.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import pl.senordeveloper.code16challenge.humanId
import pl.senordeveloper.code16challenge.model.Location
import timber.log.Timber

@BindingAdapter("location")
fun TextView.location(location: Location?) {
    Timber.d("location($humanId, $location)")
    if (location == null)
        return

    text = location.formatted
}