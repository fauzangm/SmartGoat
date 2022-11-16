package com.eduside.smartgoat.ui.home.home

import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.eduside.smartgoat.R
import com.eduside.smartgoat.databinding.FragmentHomeBinding
import com.eduside.smartgoat.ui.auth.AuthViewModel
import com.eduside.smartgoat.ui.auth.login.DialogGagalLogin
import com.eduside.smartgoat.ui.auth.register.DialogSuccesRegist
import com.eduside.smartgoat.ui.datakambing.DataKambingActivity
import com.eduside.smartgoat.util.DATETIME_FULL_FORMAT
import com.eduside.smartgoat.util.formatStringToTanggal
import com.eduside.smartgoat.util.showError
import com.eduside.smartgoat.util.showLoading
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.text.SimpleDateFormat

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewmodel: HomeViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

        val current = LocalDateTime.now()
        val selectedDate = LocalDate(current.year, current.monthOfYear, current.dayOfMonth).toString() + "T00:00:00Z"
        formatStringToTanggal(selectedDate, DATETIME_FULL_FORMAT).uppercase()
//        Log.e("current data","$current dan $selectedDate dan ${formatStringToTanggal(selectedDate, DATETIME_FULL_FORMAT).uppercase()}")
        binding.tvTanggal.text = formatStringToTanggal(selectedDate, DATETIME_FULL_FORMAT).uppercase()
        iniAction()
        initObserve()
    }

    private fun initObserve() {

        viewmodel.getRegError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalLogin()
            activity?.supportFragmentManager?.let { it1 -> bottomSheetFragment.show(it1,"DialogGagal") }
        }
        viewmodel.getRegLoading.observe(viewLifecycleOwner) {
//            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
//            showLoading(this, binding.pbSubmitRegistrasi, it)
        }
        viewmodel.getRegResponse.observe(viewLifecycleOwner) {

            it.data?.forEach { data->
                binding.tvSuhu.text = data?.suhu?.toFloat()?.toInt().toString()+"°"
                binding.tvIntensitas.text = data?.intensitas.toString()+" Lux"
                binding.tvKelembapan.text = "Kelembapan "+data?.kelembapan?.toFloat()?.toInt().toString()+"%"
                binding.tvAMonia.text = data?.amonia.toString()
            }

        }
    }

    private fun iniAction() {
        viewmodel.getSensor()
        binding.btnKambing.setOnClickListener { view ->
          startActivity(Intent(requireActivity(),DataKambingActivity::class.java))

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}