package com.boofi.openrgbandroid

import android.app.Application
import android.app.Instrumentation
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.boofi.openrgbandroid.databinding.FragmentWindowsBinding
import java.util.regex.*


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */


class ThirdFragment : Fragment() {

    private var _binding: FragmentWindowsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWindowsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val regex = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])".toRegex()
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        binding.buttonfinishsetup.setOnClickListener() {
            var ActualIP = binding.editTextIPAdress.getText().toString()
            val match = regex.matches(ActualIP)
            if(match){
                val sharePreference = context?.getSharedPreferences("RGBSettings", Context.MODE_PRIVATE)
                val editor = sharePreference?.edit()
                editor?.putString("hostIP", ActualIP);
                editor?.putBoolean("firstSetup", false)
                editor?.commit()
            } else{
                Toast.makeText(context, "This does not appear to be a valid IP address", Toast.LENGTH_LONG).show()
                vibrator.vibrate(100)
                val sharePreference = context?.getSharedPreferences("RGBSettings", Context.MODE_PRIVATE)
                val penis = sharePreference?.getString("hostIP", "null")
                val penis2 = sharePreference?.getBoolean("firstSetup", true)
                Log.d("OpenRGB", penis + penis2)

            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}