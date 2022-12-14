package com.eduside.smartgoat.ui.auth.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.eduside.smartgoat.databinding.FragmentDialogSuccesBinding
import com.eduside.smartgoat.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DialogSuccesRegist : DialogFragment() {

    private var _binding : FragmentDialogSuccesBinding ? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this frabg_primary_bottom_big_rounded.xmlgment
        _binding = FragmentDialogSuccesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnHome.setOnClickListener {
         startActivity(Intent(requireActivity(),LoginActivity::class.java))
        activity?.finishAffinity()
        }
    }


}