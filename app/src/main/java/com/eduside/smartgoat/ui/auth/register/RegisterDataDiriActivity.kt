package com.eduside.smartgoat.ui.auth.register


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eduside.smartgoat.data.local.sp.DataRegistrasiCache
import com.eduside.smartgoat.data.local.sp.model.FormatDataRegistrasi
import com.eduside.smartgoat.databinding.ActivityRegisterDatadiriBinding
import com.eduside.smartgoat.databinding.ActivitySplashBinding
import com.eduside.smartgoat.ui.auth.login.LoginActivity
import com.eduside.smartgoat.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterDataDiriActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterDatadiriBinding? = null
    private val binding get() = _binding!!
    private var name = ""
    private var email = ""
    private var pw = ""
    private var pwagain = ""
    @Inject lateinit var dataRegistrasiCache: DataRegistrasiCache
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterDatadiriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

     iniAction()

    }

    private fun iniAction() {
        binding.btnNext.setOnClickListener {

            name = binding.etUsername.text.toString().trim()
            if (name.isEmpty()) {
                binding.etUsername.error = "Wajib di isi"
                Toast.makeText(applicationContext, "Data Nama Belum Terisi", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            email = binding.etEmail.text.toString().trim()
            if (email.isEmpty()) {
                binding.etEmail.error = "Wajib di isi"
                Toast.makeText(applicationContext, "Data Email Belum Terisi", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            pw = binding.etPassword.text.toString().trim()
            if (pw.isEmpty()) {
                binding.etPassword.error = "Wajib di isi"
                Toast.makeText(applicationContext, "Data Password Belum Terisi", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            pwagain = binding.etPasswordUlangi.text.toString().trim()
            if (pwagain.isEmpty()) {
                binding.etPasswordUlangi.error = "Wajib di isi"
                Toast.makeText(applicationContext, "Data Password Belum Terisi", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (pw != pwagain) {
                Toast.makeText(applicationContext, "Data Password Tidak Sama", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            dataRegistrasiCache.dataRegistrasi = FormatDataRegistrasi(
                email = email,
                ttl = dataRegistrasiCache.dataRegistrasi?.ttl,
                lokasi_kandang = dataRegistrasiCache.dataRegistrasi?.lokasi_kandang,
                domisili = dataRegistrasiCache.dataRegistrasi?.domisili,
                nodeid = 1,
                name=name,
                password = pw,
                password_confirmation = pwagain
            )
            startActivity(Intent(this, RegisterAlamatActivity::class.java))
        }



        binding.btnTologin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}

