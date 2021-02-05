package pl.senordeveloper.code16challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import pl.senordeveloper.code16challenge.adapter.OnUserClickedListener
import pl.senordeveloper.code16challenge.adapter.UsersAdapter
import pl.senordeveloper.code16challenge.databinding.FragmentListBinding
import pl.senordeveloper.code16challenge.di.DIViewModelFactory

class ListFragment :
    Fragment(),
    DIAware,
    OnUserClickedListener {
    override val di: DI by di()

    private val mainViewModel by viewModels<MainViewModel> {
        DIViewModelFactory(di)
    }

    private lateinit var fragmentListBinding: FragmentListBinding

    private val userViewHolderAdapter = UsersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentListBinding = FragmentListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            mainViewModel = this@ListFragment.mainViewModel
            adapter = userViewHolderAdapter
        }

        fragmentListBinding.mainRecycler.layoutManager = LinearLayoutManager(requireContext())
        fragmentListBinding.mainRecycler.itemAnimator = DefaultItemAnimator()

        fragmentListBinding.mainRecycler.adapter = userViewHolderAdapter

        userViewHolderAdapter.onUserClickedListener = this

        return fragmentListBinding.root
    }

    override fun onUserClicked(user: User) {
        findNavController().navigate(ListFragmentDirections.actionMainListToDetailedFragment(user))
    }
}

