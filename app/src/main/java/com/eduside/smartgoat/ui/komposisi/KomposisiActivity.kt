package com.eduside.smartgoat.ui.komposisi


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduside.smartgoat.R
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.databinding.ActivityDetailKambingBinding
import com.eduside.smartgoat.databinding.ActivityKomposisiBinding
import com.eduside.smartgoat.databinding.ActivityLoginBinding
import com.eduside.smartgoat.databinding.FragmentDataKambingBinding
import com.eduside.smartgoat.ui.auth.register.DialogSuccesRegist
import com.eduside.smartgoat.ui.auth.register.RegisterDataDiriActivity
import com.eduside.smartgoat.ui.datakambing.DataKambingViewModel
import com.eduside.smartgoat.ui.home.HomeActivity
import com.eduside.smartgoat.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KomposisiActivity : AppCompatActivity() {
    private var _binding: ActivityKomposisiBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityKomposisiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

        initAction()
        iniObserve()
    }

    private fun initAction() {
        binding.btnKonsentrat.setOnClickListener{
            val bottomSheetFragment = DialogKonsentrat()
            bottomSheetFragment.show(supportFragmentManager,"DialogKonsentrat")
        }

        binding.btnHijauan.setOnClickListener {
            val bottomSheetFragment = DialogHijauan()
            bottomSheetFragment.show(supportFragmentManager,"DialogHijauan")
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }


    private fun iniObserve() {

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }
