package com.eduside.smartgoat.ui.home.dashboard

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.eduside.smartgoat.data.local.sp.DataCache
import com.eduside.smartgoat.data.local.sp.SessionLogin
import com.eduside.smartgoat.data.local.sp.model.FormatDataRegistrasi
import com.eduside.smartgoat.data.local.sp.model.FormatDataSwitch
import com.eduside.smartgoat.data.local.sp.model.FormatDataTimbangan
import com.eduside.smartgoat.databinding.FragmentDashboardBinding
import com.eduside.smartgoat.ui.DialogGagalGet
import com.eduside.smartgoat.ui.cctv.ImageActivity
import com.eduside.smartgoat.util.showError
import com.eduside.smartgoat.util.showLoading
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var currentPb = 0
    private var modeLampu = false
    private var modeFan = false
    private var modeTimbangan = false
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

        if (dataCache.dataSwitch != null){
            binding.switchTimbangan.isChecked = dataCache.dataSwitch?.modeTimbangan!!
            binding.switchFan.isChecked = dataCache.dataSwitch?.modeFan!!
            binding.switchLampu.isChecked = dataCache.dataSwitch?.modeLampu!!
            currentPb = dataCache.dataSwitch?.currentDimmer!!
        }
        Log.e("datacache1",dataCache.dataSwitch.toString())

//        Log.e("nilaicurret",currentPb.toString())
//        binding.progresIntentsitas.max = 10
//        ObjectAnimator.ofInt(binding.progresIntentsitas,"progress",currentPb)
//            .setDuration(500)
//            .start()

        var mMin = 0
        var mMax = 100
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.progresIntentsitas.min = mMin
            binding.progresIntentsitas.max = mMax
        }
        binding.progresIntentsitas.progress = dataCache.dataSwitch?.currentDimmer!!
        binding.progresIntentsitas.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                currentPb = p1
                dataCache.dataSwitch = FormatDataSwitch(
                    modeFan = dataCache.dataSwitch?.modeFan,
                    modeLampu = dataCache.dataSwitch?.modeLampu,
                    modeTimbangan = dataCache.dataSwitch?.modeTimbangan,
                    currentDimmer = currentPb
                )

                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = currentPb.toString()
                )
                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                viewmodel.putDimmerLampu(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })


    }

    private fun initObserve() {

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

        //SWLAMPU
        viewmodel.putSwLampuRegError.observe(viewLifecycleOwner) {
//            showError(requireActivity(),it)
//            val bottomSheetFragment = DialogGagalGet()
//            activity?.supportFragmentManager?.let { it1 -> bottomSheetFragment.show(it1,"DialogGagal") }
        }
        viewmodel.putSwLampuRegLoading.observe(viewLifecycleOwner) {
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            showLoading(requireActivity(), binding.pbSubmitRegistrasi, it)
        }
        viewmodel.putSwLampuRegResponse.observe(viewLifecycleOwner) {
            dataCache.dataTimbangan = null
        }

        //Dimmer
        viewmodel.putDimmerLampuRegError.observe(viewLifecycleOwner) {
//            showError(requireActivity(),it)
//            val bottomSheetFragment = DialogGagalGet()
//            activity?.supportFragmentManager?.let { it1 -> bottomSheetFragment.show(it1,"DialogGagal") }
        }
        viewmodel.putDimmerLampuRegLoading.observe(viewLifecycleOwner) {
            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
            showLoading(requireActivity(), binding.pbSubmitRegistrasi, it)
        }
        viewmodel.putDimmerLampuRegResponse.observe(viewLifecycleOwner) {
            dataCache.dataTimbangan = null
        }
    }

    private fun iniAction() {

        binding.clCctv.setOnClickListener {
            startActivity(Intent(requireActivity(),ImageActivity::class.java))
        }
        binding.clLampu.setOnClickListener {
            binding.clnotranparan.visibility = View.GONE
            binding.cltranparan.visibility = View.VISIBLE
        }

        binding.imgTranpaaran.setOnClickListener {
            binding.clnotranparan.visibility = View.VISIBLE
            binding.cltranparan.visibility = View.GONE
        }

        binding.switchLampu.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = "1"
                )
                viewmodel.putSwLampu(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
            }

            if (!isChecked){
                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = "0"
                )

                viewmodel.putSwLampu(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
                viewmodel.putDimmerLampu(
                    Gson().fromJson(Gson().toJson(dataCache.dataTimbangan), JsonObject::class.java)
                )
                dataCache.dataSwitch = FormatDataSwitch(
                    modeFan = dataCache.dataSwitch?.modeFan,
                    modeLampu = false,
                    modeTimbangan = dataCache.dataSwitch?.modeTimbangan,
                    currentDimmer = 0
                )
                binding.progresIntentsitas.progress = 0
            }

            dataCache.dataSwitch = FormatDataSwitch(
                modeFan = dataCache.dataSwitch?.modeFan,
                modeLampu = isChecked,
                modeTimbangan = dataCache.dataSwitch?.modeTimbangan,
                currentDimmer = dataCache.dataSwitch?.currentDimmer
            )
            Log.e("datacache2",dataCache.dataSwitch.toString())

        }

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

            dataCache.dataSwitch = FormatDataSwitch(
                modeFan = dataCache.dataSwitch?.modeFan,
                modeLampu = dataCache.dataSwitch?.modeLampu,
                modeTimbangan = isChecked,
                currentDimmer = dataCache.dataSwitch?.currentDimmer
            )

            Log.e("datacache3",dataCache.dataSwitch.toString())
        }

        binding.switchFan.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.pbSubmitRegistrasi.visibility = View.VISIBLE
                dataCache.dataTimbangan = FormatDataTimbangan(
                    value = "1"
                )
                viewmodel.putFan(
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
            dataCache.dataSwitch = FormatDataSwitch(
                modeFan = isChecked,
                modeLampu = dataCache.dataSwitch?.modeLampu,
                modeTimbangan = dataCache.dataSwitch?.modeTimbangan,
                currentDimmer = dataCache.dataSwitch?.currentDimmer
            )
            Log.e("datacache4",dataCache.dataSwitch.toString())

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}