package pl.senordeveloper.code16challenge.adapter

import androidx.recyclerview.widget.RecyclerView
import pl.senordeveloper.code16challenge.User
import pl.senordeveloper.code16challenge.databinding.ListItemUserBinding

class UserViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, onUserClickedListener: OnUserClickedListener) {
        binding.user = user
        binding.onUserClickListener = onUserClickedListener
    }
}

