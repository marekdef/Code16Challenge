package pl.senordeveloper.code16challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.di
import pl.senordeveloper.code16challenge.databinding.FragmentDetailsBinding
import pl.senordeveloper.code16challenge.di.DIViewModelFactory

class DetailsFragment :
    Fragment(),
    DIAware {
    override val di: DI by di()

    private val detailsViewModel by viewModels<DetailsViewModel> {
        DIViewModelFactory(di)
    }

    private val args by navArgs<DetailsFragmentArgs>()

    lateinit var fragmentDetailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            detailsViewModel = this@DetailsFragment.detailsViewModel
        }

        detailsViewModel.newUser(args.user)

        // Inflate the layout for this fragment
        return fragmentDetailsBinding.root
    }
}