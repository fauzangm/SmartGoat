package com.eduside.smartgoat.ui.auth.login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.eduside.smartgoat.R
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.databinding.ActivityLoginBinding
import com.eduside.smartgoat.ui.auth.register.RegisterDataDiriActivity
import com.eduside.smartgoat.ui.datakambing.DataKambingViewModel
import com.eduside.smartgoat.ui.home.HomeActivity
import com.eduside.smartgoat.ui.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: LoginViewModel by viewModels()
    private var email = ""
    private var pw = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

        initAction()
        initObserve()

    }

    private fun initObserve() {
        viewmodel.addDataKambing(DatakambingVo(
            id = 1,
            nomor = "001",
            nama = "Kambing Ahha"
        ))
        viewmodel.addDataKambing(DatakambingVo(
            id = 2,
            nomor = "002",
            nama = "Kambing Buud"
        ))

        viewmodel.addDataKambing(DatakambingVo(
            id = 3,
            nomor = "003",
            nama = "Kambing quin"
        ))
    }

    private fun initAction() {
        binding.btnDaftar.setOnClickListener {
            startActivity(Intent(this, RegisterDataDiriActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {

            email = binding.etUsername.text.toString().trim()
            if (email.isEmpty()) {
                binding.etUsername.error = "Wajib di isi"
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
            startActivity(Intent(this, MainActivity::class.java))

        }

    }
}