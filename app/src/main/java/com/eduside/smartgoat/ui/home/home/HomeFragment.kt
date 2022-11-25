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
import com.eduside.smartgoat.ui.DialogGagalGet
import com.eduside.smartgoat.ui.auth.AuthViewModel
import com.eduside.smartgoat.ui.auth.login.DialogGagalLogin
import com.eduside.smartgoat.ui.auth.register.DialogSuccesRegist
import com.eduside.smartgoat.ui.datakambing.DataKambingActivity
import com.eduside.smartgoat.ui.komposisi.KomposisiActivity
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

        val current = LocalDateTime.now()
        val selectedDate = LocalDate(
            current.year,
            current.monthOfYear,
            current.dayOfMonth
        ).toString() + "T00:00:00Z"
        formatStringToTanggal(selectedDate, DATETIME_FULL_FORMAT).uppercase()
//        Log.e("current data","$current dan $selectedDate dan ${formatStringToTanggal(selectedDate, DATETIME_FULL_FORMAT).uppercase()}")
        binding.tvTanggal.text =
            formatStringToTanggal(selectedDate, DATETIME_FULL_FORMAT).uppercase()
        iniAction()
        initObserve()
    }

    private fun initObserve() {

        viewmodel.getRegError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }
        }
        viewmodel.getRegLoading.observe(viewLifecycleOwner) {
//            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
//            showLoading(this, binding.pbSubmitRegistrasi, it)
        }
        viewmodel.getRegResponse.observe(viewLifecycleOwner) {

            binding.tvSuhu.text = it.data?.suhu?.toFloat()?.toInt().toString() + "°"
            binding.tvIntensitas.text = it.data?.intensitas.toString() + " Lux"
            binding.tvKelembapan.text = it.message!![2]
            binding.tvAMonia.text = it.message[0]
            binding.tvStatus.text = it.message[1]


        }

        viewmodel.getKambingRegError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }
        }
        viewmodel.getKambingRegLoading.observe(viewLifecycleOwner) {
//            binding..visibility = View.VISIBLE
//            showLoading(this, binding.pbSubmitRegistrasi, it)
        }
        viewmodel.getKambingRegResponse.observe(viewLifecycleOwner) {

        }

    }

    private fun iniAction() {
        viewmodel.getSensor()
        viewmodel.getDataKambing()
        binding.btnKambing.setOnClickListener {
            val intent = Intent(requireActivity(), DataKambingActivity::class.java)
            intent.putExtra(DataKambingActivity.MOVE,"1")
            startActivity(intent)

        }

        binding.btnKomposisi.setOnClickListener {
            val intent = Intent(requireActivity(), DataKambingActivity::class.java)
            intent.putExtra(DataKambingActivity.MOVE,"2")
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}