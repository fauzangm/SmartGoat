package com.eduside.smartgoat.ui.cctv


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.eduside.smartgoat.R
import com.eduside.smartgoat.databinding.ActivityCctvBinding
import com.eduside.smartgoat.ui.DialogGagalGet
import com.eduside.smartgoat.util.showLoading
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class ImageActivity : AppCompatActivity() {
    private var _binding: ActivityCctvBinding? = null
    private val viewmodel: ImageViewModel by viewModels()
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCctvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

        viewmodel.getImage()
        initAction()
        initObserve()
        getRealtime()

    }

    private fun getRealtime() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewmodel.getImage()
            getRealtime()
        }, 30000)
    }

    private fun initObserve() {

        viewmodel.GetRegError.observe(this) {
            val bottomSheetFragment = DialogGagalGet()
            bottomSheetFragment.show(supportFragmentManager, "DialogGagal")
        }
        viewmodel.GetRegLoading.observe(this) {
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            showLoading(this, binding.pbSubmitRegistrasi, it)
        }
        viewmodel.GetRegResponse.observe(this) {
            val lenght = it.data?.size
            try {
                if (lenght != null) {

                    try {
                        if (lenght > 1) {
                            val url = it.data.get(0).toString()
                            Timber.e("cek url $url")
                            Glide
                                .with(applicationContext)
                                .load(it.data?.get(lenght - 1))
                                .centerCrop()
                                .placeholder(R.drawable.ic_defaultimage)
                                .into(binding.imgCctv1)
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    try {
                        if (lenght > 2) {
                            Glide
                                .with(applicationContext)
                                .load(it.data?.get(lenght - 2))
                                .centerCrop()
                                .placeholder(R.drawable.ic_defaultimage)
                                .into(binding.imgCctv2)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    try {
                        if (lenght > 3) {
                            Glide
                                .with(applicationContext)
                                .load(it.data?.get(lenght - 3))
                                .centerCrop()
                                .placeholder(R.drawable.ic_defaultimage)
                                .into(binding.imgCctv3)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    try {
                        if (lenght > 4) {
                            Glide
                                .with(applicationContext)
                                .load(it.data?.get(lenght - 4))
                                .centerCrop()
                                .placeholder(R.drawable.ic_defaultimage)
                                .into(binding.imgCctv4)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    try {
                        if (lenght > 5) {
                            Glide
                                .with(applicationContext)
                                .load(it.data?.get(lenght - 5))
                                .centerCrop()
                                .placeholder(R.drawable.ic_defaultimage)
                                .into(binding.imgCctv5)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun initAction() {
        binding.imgback.setOnClickListener {
            onBackPressed()
        }
    }
}

