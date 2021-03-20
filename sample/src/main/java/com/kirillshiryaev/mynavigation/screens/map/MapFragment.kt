package com.kirillshiryaev.mynavigation.screens.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kirillshiryaev.mynavigation.R
import com.kirillshiryaev.mynavigation.databinding.FragmentMapBinding
import com.kirillshiryaev.unav.Screen
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MapFragment

class MapFragment : Fragment(), com.kirillshiryaev.unav.Screen {

    private var _binding: FragmentMapBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() = with(binding) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as MapFragment
        mapFragment.setup(
            apiKey = "rurblu0539",
            center = LonLat(37.6175, 55.7520),
            zoom = 16.0
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}