package com.eduside.smartgoat.ui.home.profiles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.eduside.smartgoat.data.local.sp.SessionLogin
import com.eduside.smartgoat.databinding.FragmentProfileBinding
import com.eduside.smartgoat.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    @Inject lateinit var sessionLogin: SessionLogin
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {
        binding.tvNameProfile.text = sessionLogin.dataUser?.name
        binding.tvEmail.text = sessionLogin.dataUser?.email
        binding.tvttl.text = sessionLogin.dataUser?.ttl
        binding.tvDomisili.text = sessionLogin.dataUser?.domisili
        binding.tvLokKandang.text = sessionLogin.dataUser?.lokasi_kandang
        initAction()
    }

    private fun initAction() {
        binding.btnLogout.setOnClickListener {
            Intent(requireActivity(), LoginActivity::class.java).also {
//                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
                sessionLogin.clear()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
