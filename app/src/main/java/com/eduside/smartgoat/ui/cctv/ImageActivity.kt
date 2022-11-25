package com.eduside.smartgoat.ui.cctv


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.eduside.smartgoat.R
import com.eduside.smartgoat.databinding.ActivityCctvBinding
import com.eduside.smartgoat.databinding.ActivitySplashBinding
import com.eduside.smartgoat.ui.DialogGagalGet
import com.eduside.smartgoat.ui.auth.AuthViewModel
import com.eduside.smartgoat.ui.auth.login.DialogGagalLogin
import com.eduside.smartgoat.ui.auth.register.DialogSuccesRegist
import com.eduside.smartgoat.util.showLoading
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ImageActivity : AppCompatActivity() {
    private var _binding : ActivityCctvBinding? = null
    private val viewmodel: ImageViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCctvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initUi()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun initUi() {

        viewmodel.getImage()
        initAction()
        initObserve()

    }

    private fun initObserve() {

        viewmodel.GetRegError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager,"DialogGagal")
        }
        viewmodel.GetRegLoading.observe(this) {
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            showLoading(this, binding.pbSubmitRegistrasi, it)
        }
        viewmodel.GetRegResponse.observe(this) {
            it.data?.forEach {
                Log.e("itcctv",it.toString())
            }

            Glide
                .with(applicationContext)
                .load(it.data?.get(0))
                .centerCrop()
                .placeholder(R.drawable.ic_defaultimage)
                .into(binding.imgCctv1)

            Glide
                .with(applicationContext)
                .load(it.data?.get(1))
                .centerCrop()
                .placeholder(R.drawable.ic_defaultimage)
                .into(binding.imgCctv2)

            Glide
                .with(applicationContext)
                .load(it.data?.get(2))
                .centerCrop()
                .placeholder(R.drawable.ic_defaultimage)
                .into(binding.imgCctv3)

            Glide
                .with(applicationContext)
                .load(it.data?.get(3))
                .centerCrop()
                .placeholder(R.drawable.ic_defaultimage)
                .into(binding.imgCctv4)

            Glide
                .with(applicationContext)
                .load(it.data?.get(3))
                .centerCrop()
                .placeholder(R.drawable.ic_defaultimage)
                .into(binding.imgCctv5)
        }

    }

    private fun initAction() {
    }
}

