package com.eduside.smartgoat.ui.auth.register


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.eduside.smartgoat.databinding.ActivityRegisterAlamatBinding
import com.eduside.smartgoat.databinding.ActivitySplashBinding
import com.eduside.smartgoat.util.DATETIME_FULL_FORMAT
import com.eduside.smartgoat.util.formatStringToTanggal
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.LocalDate
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class RegisterAlamatActivity : AppCompatActivity() {
    private var _binding : ActivityRegisterAlamatBinding? = null
    private val binding get() = _binding!!

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

        binding.cbAgreement.setOnCheckedChangeListener { buttonView, isChecked ->
            println("checkboxmove $isChecked")
            validUi = isChecked
        }

        iniAction()

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

            val bottomSheetFragment = DialogSuccesRegist()
            bottomSheetFragment.show(supportFragmentManager,"DialogSucces")



        }
    }
}

