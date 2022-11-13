package com.eduside.smartgoat.ui.datakambing

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduside.smartgoat.databinding.FragmentDataKambingBinding
import com.eduside.smartgoat.databinding.FragmentHomeBinding
import com.eduside.smartgoat.ui.auth.login.LoginViewModel
import com.eduside.smartgoat.ui.home.home.HomeViewModel
import javax.inject.Inject

class DataKambingFragment : Fragment() {

    private var _binding: FragmentDataKambingBinding? = null
    @Inject lateinit var adapter : DataKambingAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewmodel =
            ViewModelProvider(this).get(DataKambingViewModel::class.java)

        _binding = FragmentDataKambingBinding.inflate(inflater, container, false)
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
        iniObserve()
        binding.rvdatakambing.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvdatakambing.adapter = adapter

    }

    private fun iniObserve() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}