package com.kirillshiryaev.mynavigation.screens.chooser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kirillshiryaev.mynavigation.MainActivity
import com.kirillshiryaev.mynavigation.databinding.FragmentStartBinding
import com.kirillshiryaev.mynavigation.screens.counter.CountFragment
import com.kirillshiryaev.mynavigation.screens.map.MapFragment
import com.kirillshiryaev.mynavigation.screens.stringer.StringerFragment
import com.kirillshiryaev.unav.Screen

class StartFragment : Fragment(), com.kirillshiryaev.unav.Screen {

    private var _binding: FragmentStartBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() = with(binding) {

        buttonGoCounterScreen.setOnClickListener {
            (requireActivity() as MainActivity).router.navigateTo(CountFragment::class, true)
        }

        buttonGoMap.setOnClickListener {
            (requireActivity() as MainActivity).router.navigateToWithoutSaveState(MapFragment::class)
        }

        buttonGoTextScreen.setOnClickListener {
            (requireActivity() as MainActivity).router.navigateTo(StringerFragment::class)
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}