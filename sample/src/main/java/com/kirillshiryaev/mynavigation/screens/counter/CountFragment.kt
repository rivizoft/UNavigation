package com.kirillshiryaev.mynavigation.screens.counter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kirillshiryaev.mynavigation.MainActivity
import com.kirillshiryaev.mynavigation.R
import com.kirillshiryaev.mynavigation.databinding.FragmentCountBinding
import com.kirillshiryaev.mynavigation.screens.stringer.StringerFragment
import com.kirillshiryaev.unav.Screen

class CountFragment : Fragment(R.layout.fragment_count), com.kirillshiryaev.unav.Screen {

    private val viewModel by viewModels<CountViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CountViewModel(requireActivity().application) as T
            }
        }
    })
    private var _binding: FragmentCountBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initViews()
    }

    private fun initObservers() {
        viewModel.count.observe(viewLifecycleOwner) {
            binding.tvCount.text = it.toString()
        }
    }

    private fun initViews() = with(binding) {
        buttonClickAdd.setOnClickListener {
            viewModel.add()
        }
        buttonNextScreen.setOnClickListener {
            (requireActivity() as MainActivity).router.navigateToWithoutSaveState(StringerFragment::class)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}