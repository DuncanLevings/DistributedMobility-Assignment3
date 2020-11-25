package sheridan.levings.assignment3.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import sheridan.levings.assignment3.databinding.FragmentDataDetailsBinding

class DataDetailsFragment : Fragment() {

    private val dataDetailsViewModel: DataDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDataDetailsBinding.inflate(inflater, container, false)

        val flowersProperty = DataDetailsFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = DataDetailsViewModelFactory(flowersProperty, application)
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory).get(DataDetailsViewModel::class.java)

        return binding.root
    }
}