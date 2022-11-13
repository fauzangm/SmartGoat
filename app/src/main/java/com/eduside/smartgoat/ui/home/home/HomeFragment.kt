package com.eduside.smartgoat.ui.home.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.eduside.smartgoat.R
import com.eduside.smartgoat.databinding.FragmentHomeBinding
import com.eduside.smartgoat.ui.datakambing.DataKambingActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        try {
            initUi()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun initUi() {
        iniAction()
    }

    private fun iniAction() {
        binding.btnKambing.setOnClickListener { view ->
          startActivity(Intent(requireActivity(),DataKambingActivity::class.java))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}