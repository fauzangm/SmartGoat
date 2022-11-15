package com.eduside.smartgoat.ui.auth.register


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eduside.smartgoat.data.local.sp.DataRegistrasiCache
import com.eduside.smartgoat.data.local.sp.model.FormatDataRegistrasi
import com.eduside.smartgoat.data.repository.Auth.AuthRepository
import com.eduside.smartgoat.databinding.ActivityRegisterAlamatBinding
import com.eduside.smartgoat.databinding.ActivitySplashBinding
import com.eduside.smartgoat.ui.auth.AuthViewModel
import com.eduside.smartgoat.ui.auth.login.DialogGagalLogin
import com.eduside.smartgoat.ui.auth.login.LoginViewModel
import com.eduside.smartgoat.util.DATETIME_FULL_FORMAT
import com.eduside.smartgoat.util.formatStringToTanggal
import com.eduside.smartgoat.util.showError
import com.eduside.smartgoat.util.showLoading
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.LocalDate
import splitties.activities.start
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterAlamatActivity : AppCompatActivity() {
    private var _binding : ActivityRegisterAlamatBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var dataRegistrasiCache: DataRegistrasiCache
    private val viewmodel: AuthViewModel by viewModels()
    private var tanggal = ""
    private var domisili = ""
    private var lokasi = ""
    private var selectedDate = ""
    private var validUi = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterAlamatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            initUi()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun initUi() {

        binding.pbSubmitRegistrasi.visibility = View.GONE
        binding.cbAgreement.setOnCheckedChangeListener { buttonView, isChecked ->
            println("checkboxmove $isChecked")
            validUi = isChecked
        }

        iniAction()
        initObserve()

    }

    private fun initObserve() {

        viewmodel.postRegError.observe(this) {
            val bottomSheetFragment = DialogGagalLogin()
            bottomSheetFragment.show(supportFragmentManager,"DialogGagal")
        }
        viewmodel.postRegLoading.observe(this) {
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            showLoading(this, binding.pbSubmitRegistrasi, it)
        }
        viewmodel.postRegResponse.observe(this) {
            dataRegistrasiCache.dataRegistrasi = null
            val bottomSheetFragment = DialogSuccesRegist()
            bottomSheetFragment.show(supportFragmentManager,"DialogSucces")
        }

    }

    private fun iniAction() {

        binding.tvtanggal.setOnClickListener {

            val datePicker = Calendar.getInstance()
            val dateDialog =
                DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                    datePicker[Calendar.YEAR] = year
                    datePicker[Calendar.MONTH] = month
                    datePicker[Calendar.DAY_OF_MONTH] = day
                    val bulan = month + 1
                    selectedDate = LocalDate(year, bulan, day).toString() + "T00:00:00Z"
                    Timber.e("Tanggalnya $selectedDate")
                    binding.tvtanggal.text = formatStringToTanggal(selectedDate, DATETIME_FULL_FORMAT).uppercase()
                }
            DatePickerDialog(
                this@RegisterAlamatActivity,dateDialog,
                datePicker[Calendar.YEAR],
                datePicker[Calendar.MONTH],
                datePicker[Calendar.DAY_OF_MONTH]
            ).show()
        }

        binding.btnNext.setOnClickListener {
            tanggal = binding.tvtanggal.text.toString().trim()
            if (tanggal.isEmpty()) {
                binding.tvtanggal.error = "Wajib di isi"
                Toast.makeText(applicationContext, "Data Tanggal Belum Terisi", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            domisili = binding.etDomisili.text.toString().trim()
            if (domisili.isEmpty()) {
                binding.etDomisili.error = "Wajib di isi"
                Toast.makeText(applicationContext, "Data Domisili Belum Terisi", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            lokasi = binding.etLokasi.text.toString().trim()
            if (lokasi.isEmpty()) {
                binding.etLokasi.error = "Wajib di isi"
                Toast.makeText(applicationContext, "Data Lokasi Belum Terisi", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (!validUi){
                Toast.makeText(applicationContext,"Checkbox belum terisi",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            dataRegistrasiCache.dataRegistrasi = FormatDataRegistrasi(
                email = dataRegistrasiCache.dataRegistrasi?.email,
                ttl = tanggal,
                lokasi_kandang = lokasi,
                domisili = domisili,
                nodeid = dataRegistrasiCache.dataRegistrasi?.nodeid,
                name = dataRegistrasiCache.dataRegistrasi?.name,
                password = dataRegistrasiCache.dataRegistrasi?.password,
                password_confirmation = dataRegistrasiCache.dataRegistrasi?.password_confirmation
            )

            viewmodel.postRegistrasiWP(
                Gson().fromJson(Gson().toJson(dataRegistrasiCache.dataRegistrasi), JsonObject::class.java)
            )

        }
    }
}

