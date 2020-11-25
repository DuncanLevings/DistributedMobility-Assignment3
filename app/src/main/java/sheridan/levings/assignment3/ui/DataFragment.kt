package sheridan.levings.assignment3.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration

import sheridan.levings.assignment3.databinding.FragmentDataBinding
import java.util.Observer

class DataFragment : Fragment() {

    private val viewModel: DataViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDataBinding.inflate(inflater)

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(divider)
        val adapter = FlowerListAdapter(FlowerListAdapter.OnClickListener {
            viewModel.displayFlowerDetails(it)
        })

        binding.recyclerView.adapter = adapter

        viewModel.getFlowers().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner) {
            if ( null != it ) {
                this.findNavController().navigate(DataFragmentDirections.actionShowDataDetails(it))
                viewModel.displayFlowerDetailsComplete()
            }
        }

        return binding.root
    }
}