package com.eduside.smartgoat.ui.datakambing


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduside.smartgoat.R
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.data.local.sp.DataCache
import com.eduside.smartgoat.databinding.ActivityLoginBinding
import com.eduside.smartgoat.databinding.FragmentDataKambingBinding
import com.eduside.smartgoat.ui.DialogGagalGet
import com.eduside.smartgoat.ui.auth.register.RegisterDataDiriActivity
import com.eduside.smartgoat.ui.datakambing.DataKambingViewModel
import com.eduside.smartgoat.ui.home.HomeActivity
import com.eduside.smartgoat.ui.home.MainActivity
import com.eduside.smartgoat.ui.komposisi.KomposisiActivity
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import splitties.bundle.putExtras
import splitties.resources.int
import javax.inject.Inject

@AndroidEntryPoint
class DataKambingActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var _binding: FragmentDataKambingBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: DataKambingViewModel by viewModels()
    private var move = ""
    @Inject
    lateinit var adapter : DataKambingAdapter
    @Inject lateinit var dataCache: DataCache
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentDataKambingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            initUi()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initUi() {

        if (intent!=null){
            move = intent.getStringExtra(MOVE).toString()
        }

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null){
                    searchDatabase(query)
                }
                return true
            }

        })
        iniObserve()
        binding.rvdatakambing.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvdatakambing.adapter = adapter

        initAction()

    }

    private fun initAction(){
        binding.imgback.setOnClickListener {
            onBackPressed()
        }
    }

    private fun iniObserve() {
    viewmodel.getDataKambing().observe(this){ data->
            adapter.submitList(data)
            Log.e("datakambing",data.toString())
        }
    }

    companion object{
        const val MOVE ="MOVE"
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
            viewmodel.searchDataNamaKambing(searchQuery).observe(this) { list ->
                list.let {
                    adapter.submitList(it)
                }
            }
        }

    @Subscribe
    fun onItemNpwpdClickedEventHandler(event: ItemDataKambingEvent) {
        if (move == "1"){
            val intent =Intent(this,DetailKambingActivity::class.java)
            intent.putExtra(DetailKambingActivity.BOBOTLAHIR,event.datakambing.bobotLahir)
            intent.putExtra(DetailKambingActivity.BOBOTSEKARANG,event.datakambing.bobotSekarang)
            intent.putExtra(DetailKambingActivity.JENISKAMBING,event.datakambing.ras)
            intent.putExtra(DetailKambingActivity.LOKASI,event.datakambing.lokasi)
            intent.putExtra(DetailKambingActivity.JENISKELAMIN,event.datakambing.gender)
            intent.putExtra(DetailKambingActivity.RAS,event.datakambing.ras)
            intent.putExtra(DetailKambingActivity.RFID,event.datakambing.rfid)
            intent.putExtra(DetailKambingActivity.RFIDBETINA,event.datakambing.rfidBetina)
            intent.putExtra(DetailKambingActivity.RFIDJANTAN,event.datakambing.rfidJantan)
            intent.putExtra(DetailKambingActivity.SAUDARA,event.datakambing.saudara)
            intent.putExtra(DetailKambingActivity.TANGGALLAHIR,event.datakambing.ttl)
            intent.putExtra(DetailKambingActivity.NOKAMBING,event.datakambing.id.toString())
            intent.putExtra(DetailKambingActivity.WARNA,event.datakambing.warna)
            startActivity(intent)
        }

        if (move == "2"){
            val intent = Intent(this,KomposisiActivity::class.java)
            dataCache.put(DataCache.IDKAMBING,event.datakambing.id.toString())
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
    }
