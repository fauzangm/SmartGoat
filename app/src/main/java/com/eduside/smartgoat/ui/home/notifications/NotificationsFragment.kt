package com.eduside.smartgoat.ui.home.notifications

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eduside.smartgoat.R
import com.eduside.smartgoat.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
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
        binding.btnSemua.setBackgroundColor(resources.getColor(R.color.colorbrown))
        binding.btnSemua.setTextColor(resources.getColor(R.color.white))

        initAction()

    }

    private fun initAction() {
        binding.btnSemua.setOnClickListener {
            binding.btnSemua.setBackgroundColor(resources.getColor(R.color.colorbrown))
            binding.btnSemua.setTextColor(resources.getColor(R.color.white))
            binding.btnHijauan.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnHijauan.setTextColor(resources.getColor(R.color.black))
            binding.btnKonsentrat.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnKonsentrat.setTextColor(resources.getColor(R.color.black))
            binding.btnKtpK.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnKtpK.setTextColor(resources.getColor(R.color.black))
        }
        binding.btnHijauan.setOnClickListener {
            binding.btnHijauan.setBackgroundColor(resources.getColor(R.color.colorbrown))
            binding.btnHijauan.setTextColor(resources.getColor(R.color.white))
            binding.btnSemua.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnSemua.setTextColor(resources.getColor(R.color.black))
            binding.btnKonsentrat.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnKonsentrat.setTextColor(resources.getColor(R.color.black))
            binding.btnKtpK.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnKtpK.setTextColor(resources.getColor(R.color.black))
        }
        binding.btnKonsentrat.setOnClickListener {
            binding.btnKonsentrat.setBackgroundColor(resources.getColor(R.color.colorbrown))
            binding.btnKonsentrat.setTextColor(resources.getColor(R.color.white))
            binding.btnSemua.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnSemua.setTextColor(resources.getColor(R.color.black))
            binding.btnHijauan.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnHijauan.setTextColor(resources.getColor(R.color.black))
            binding.btnKtpK.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnKtpK.setTextColor(resources.getColor(R.color.black))
        }
        binding.btnKtpK.setOnClickListener {
            binding.btnKtpK.setBackgroundColor(resources.getColor(R.color.colorbrown))
            binding.btnKtpK.setTextColor(resources.getColor(R.color.white))
            binding.btnSemua.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnSemua.setTextColor(resources.getColor(R.color.black))
            binding.btnHijauan.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnHijauan.setTextColor(resources.getColor(R.color.black))
            binding.btnKonsentrat.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_btn_white_rounded))
            binding.btnKonsentrat.setTextColor(resources.getColor(R.color.black))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}