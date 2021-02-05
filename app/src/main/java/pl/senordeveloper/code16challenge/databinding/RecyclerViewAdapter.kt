package pl.senordeveloper.code16challenge.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.senordeveloper.code16challenge.User
import pl.senordeveloper.code16challenge.adapter.UsersAdapter
import pl.senordeveloper.code16challenge.humanId
import timber.log.Timber

@BindingAdapter("adapter", "users", requireAll = true)
fun RecyclerView.bindUser(adapter: UsersAdapter, users: List<User>?) {
    Timber.d("bindUser($humanId, $adapter, $users)")
    adapter.setData(users ?: emptyList())
}