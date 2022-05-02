package hu.bme.aut.dogspecies.ui.list

import android.os.Bundle
import android.telecom.Call
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

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.detailButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionToDetail())
        }
        return binding.root
    }
}