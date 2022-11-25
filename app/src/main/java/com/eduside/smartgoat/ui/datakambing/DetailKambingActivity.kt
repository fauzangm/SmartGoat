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
import okhttp3.internal.authenticator.JavaNetAuthenticator
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
        if (intent!=null){
            binding.tvRfid.text = intent.getStringExtra(RFID)
            binding.tvNokambing.text = intent.getStringExtra(NOKAMBING)
            binding.tvJenisKambing.text = intent.getStringExtra(JENISKAMBING)
            binding.tvBobotLahir.text = intent.getStringExtra(BOBOTLAHIR)
            binding.tvRas.text = intent.getStringExtra(RAS)
            binding.tvBobotSekarang.text = intent.getStringExtra(BOBOTSEKARANG)
            binding.tvJenisKelamin.text = intent.getStringExtra(JENISKELAMIN)
            binding.tvTanggallahir.text = intent.getStringExtra(TANGGALLAHIR)
            binding.tvSaudara.text = intent.getStringExtra(SAUDARA)
            binding.tvWarna.text = intent.getStringExtra(WARNA)
            binding.tvLokasi.text = intent.getStringExtra(LOKASI)
            binding.tvRfidBetina.text = intent.getStringExtra(RFIDBETINA)
            binding.tvRfidJantan.text = intent.getStringExtra(RFIDJANTAN)
        }

        initAction()
    }


    companion object{
        const val RFID = "RFID"
        const val NOKAMBING = "NOKAMBING"
        const val JENISKAMBING = "JENISKAMBING"
        const val BOBOTLAHIR = "BOBOTLAHIR"
        const val RAS = "RAS"
        const val BOBOTSEKARANG = "BOBOTSEKARANG"
        const val JENISKELAMIN = "JENISKELAMIN"
        const val SAUDARA = "SAUDARA"
        const val WARNA = "WARNA"
        const val LOKASI = "LOKASI"
        const val RFIDBETINA = "RFIDBETINA"
        const val RFIDJANTAN = "RFIDJANTAN"
        const val TANGGALLAHIR = "TANGGALLAHIR"
    }

    private fun initAction() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    }
