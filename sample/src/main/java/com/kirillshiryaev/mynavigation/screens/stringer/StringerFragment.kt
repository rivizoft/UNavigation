package com.kirillshiryaev.mynavigation.screens.stringer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.kirillshiryaev.mynavigation.MainActivity
import com.kirillshiryaev.mynavigation.R
import com.kirillshiryaev.mynavigation.databinding.FragmentStringerBinding
import com.kirillshiryaev.mynavigation.screens.counter.CountFragment
import com.kirillshiryaev.unav.Screen

class StringerFragment : Fragment(R.layout.fragment_stringer), com.kirillshiryaev.unav.Screen {

    private val _viewModel by viewModels<StringerViewModel>()

    private var _binding: FragmentStringerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStringerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObserves()

        (requireActivity() as MainActivity).router.removeScreenFromChain(CountFragment::class)
    }

    private fun initObserves() {
        _viewModel.text.observe(viewLifecycleOwner) { text ->
            binding.tvResultString.text = text
        }
    }

    private fun initViews() = with(binding) {
        etStringInput.addTextChangedListener { text ->
            text?.let { _viewModel.addText(it.toString()) }
        }
    }
}