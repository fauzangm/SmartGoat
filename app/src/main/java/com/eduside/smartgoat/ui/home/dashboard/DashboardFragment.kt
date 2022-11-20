package com.eduside.smartgoat.ui.home.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.eduside.smartgoat.data.local.sp.DataCache
import com.eduside.smartgoat.data.local.sp.SessionLogin
import com.eduside.smartgoat.data.local.sp.model.FormatDataRegistrasi
import com.eduside.smartgoat.data.local.sp.model.FormatDataTimbangan
import com.eduside.smartgoat.databinding.FragmentDashboardBinding
import com.eduside.smartgoat.ui.DialogGagalGet
import com.eduside.smartgoat.ui.auth.login.DialogGagalLogin
import com.eduside.smartgoat.ui.datakambing.DataKambingActivity
import com.eduside.smartgoat.ui.home.home.HomeViewModel
import com.eduside.smartgoat.ui.home.profiles.ProfileViewModel
import com.eduside.smartgoat.ui.komposisi.KomposisiActivity
import com.eduside.smartgoat.util.DATETIME_FULL_FORMAT
import com.eduside.smartgoat.util.formatStringToTanggal
import com.eduside.smartgoat.util.showLoading
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val viewmodel: DashboardViewModel by viewModels()
    @Inject lateinit var dataCache: DataCache
    @Inject lateinit var sessionLogin: SessionLogin
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
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

        iniAction()
        initObserve()
    }

    private fun initObserve() {

        binding.switchTimbangan.isChecked = sessionLogin.getBoolean(SessionLogin.MODETIMBANGAN)
        binding.switchFan.isChecked = sessionLogin.getBoolean(SessionLogin.MODEFAN)
        //TImbangan
        viewmodel.putRegError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 -> bottomSheetFragment.show(it1,"DialogGagal") }
        }
        viewmodel.putRegLoading.observe(viewLifecycleOwner) {
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            showLoading(requireActivity(), binding.pbSubmitRegistrasi, it)
        }
        viewmodel.putRegResponse.observe(viewLifecycleOwner) {
            dataCache.dataTimbangan = null
        }

        //FAN
        viewmodel.putFanRegError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 -> bottomSheetFragment.show(it1,"DialogGagal") }
        }
        viewmodel.putFanRegLoading.observe(viewLifecycleOwner) {
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            showLoading(requireActivity(), binding.pbSubmitRegistrasi, it)
        }
        viewmodel.putFanRegResponse.observe(viewLifecycleOwner) {
            dataCache.dataTimbangan = null
        }
    }

    private fun iniAction() {
        binding.switchTimbangan.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = "1"
                )
                viewmodel.putTimbangna(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
            } else{

                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = "0"
                )
                viewmodel.putTimbangna(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
            }

            sessionLogin.put(SessionLogin.MODETIMBANGAN,isChecked)

        }

        binding.switchFan.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = "1"
                )
                viewmodel.putTimbangna(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
            } else{

                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = "0"
                )
                viewmodel.putFan(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
            }
            sessionLogin.put(SessionLogin.MODEFAN,isChecked)

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}