package pl.senordeveloper.code16challenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.senordeveloper.code16challenge.User
import pl.senordeveloper.code16challenge.databinding.ListItemUserBinding
import timber.log.Timber

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>(), OnUserClickedListener {
    var onUserClickedListener: OnUserClickedListener? = null

    private val asyncListDiffer: AsyncListDiffer<User> = AsyncListDiffer(this, object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
            oldItem.firstName == newItem.firstName
                && oldItem.lastName == newItem.lastName
                && oldItem.picture == newItem.picture
    })

    private var users: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder = UserViewHolder(
        ListItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(users[position], this)

    override fun getItemCount(): Int = users.size

    fun setData(users: List<User>) {
        Timber.d("setData($users)")
        this.users = users
        asyncListDiffer.submitList(users)
    }

    override fun onUserClicked(user: User) {
        onUserClickedListener?.onUserClicked(user)
    }
}

interface OnUserClickedListener {
    fun onUserClicked(user: User)
}