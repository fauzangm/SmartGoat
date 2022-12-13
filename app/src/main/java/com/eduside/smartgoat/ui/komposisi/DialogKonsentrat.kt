package com.eduside.smartgoat.ui.komposisi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.eduside.smartgoat.data.local.sp.DataCache
import com.eduside.smartgoat.databinding.FragmentDialogKonstrantBinding
import com.eduside.smartgoat.databinding.FragmentDialogSuccesBinding
import com.eduside.smartgoat.ui.DialogGagalGet
import com.eduside.smartgoat.ui.auth.login.LoginActivity
import com.eduside.smartgoat.ui.home.notifications.NotificationsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DialogKonsentrat : DialogFragment() {


    private val viewmodel: KomposisiViewModel by viewModels()
    private var _binding : FragmentDialogKonstrantBinding ? = null
    @Inject lateinit var dataCache: DataCache
    private var id = ""
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this frabg_primary_bottom_big_rounded.xmlgment
        _binding = FragmentDialogKonstrantBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initUi()
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun initUi() {
        id = dataCache.getString(DataCache.IDKAMBING).toString()
        initObserve()
        viewmodel.getDataKambing(id)
    }



    private fun initObserve() {
        viewmodel.getKonsentratError.observe(viewLifecycleOwner) {
            val bottomSheetFragment = DialogGagalGet()
            activity?.supportFragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    "DialogGagal"
                )
            }
        }
        viewmodel.getKonsentratLoading.observe(viewLifecycleOwner) {
//            binding.pbSubmitRegistrasi.visibility = View.VISIBLE
//            showLoading(this, binding.pbSubmitRegistrasi, it)
        }
        viewmodel.getKonsentratResponse.observe(viewLifecycleOwner) {
            it.data?.konsentrat?.forEach { data->
                binding.tvthankx.text ="Jadi konsentrat yang dibutuhkan oleh kambing No.$id  dengan bobot ${data?.bobotSekarang} Kg dan umur ${data?.umur} bulan adalah sebesar ${data?.konsentrat} Kg per hari atau setara ${data?.presentase} dari bobot kambing"
            }

        }
    }


}