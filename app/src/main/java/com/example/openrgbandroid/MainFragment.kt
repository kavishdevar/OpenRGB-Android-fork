package com.boofi.openrgbandroid

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.boofi.openrgbandroid.R
import com.boofi.openrgbandroid.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharePreference = context?.getSharedPreferences("RGBSettings", Context.MODE_PRIVATE)
            val firststart = sharePreference?.getBoolean("firstSetup", true) // is this the first start?
            if(firststart == true) {
        Log.d("OpenRGB", "Yes it is first time lolll")
                findNavController().navigate(R.id.GoToFirstSetup)
            } else {
            Log.e("OpenRGB", "PENIS")
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}