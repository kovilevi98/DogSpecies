package hu.bme.aut.dogspecies.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.dogspecies.R
import hu.bme.aut.dogspecies.databinding.FragmentListBinding
import hu.bme.aut.dogspecies.ui.list.ListViewModel

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        /*binding.detailButton.setOnClickListener {
            findNavController().navigate(ListFragment.)
        }*/
        return binding.root
    }

}