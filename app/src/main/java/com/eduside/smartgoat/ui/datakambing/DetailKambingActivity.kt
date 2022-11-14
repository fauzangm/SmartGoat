package com.eduside.smartgoat.ui.datakambing


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
import com.eduside.smartgoat.databinding.ActivityLoginBinding
import com.eduside.smartgoat.databinding.FragmentDataKambingBinding
import com.eduside.smartgoat.ui.auth.register.RegisterDataDiriActivity
import com.eduside.smartgoat.ui.datakambing.DataKambingViewModel
import com.eduside.smartgoat.ui.home.HomeActivity
import com.eduside.smartgoat.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailKambingActivity : AppCompatActivity() {
    private var _binding: ActivityDetailKambingBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: DataKambingViewModel by viewModels()
    @Inject
    lateinit var adapter : DataKambingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailKambingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

    }

    private fun iniObserve() {

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }
