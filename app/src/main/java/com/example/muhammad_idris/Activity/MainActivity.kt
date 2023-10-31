package com.example.muhammad_idris.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.muhammad_idris.Model.DataItem
import com.example.muhammad_idris.Model.Response
import com.example.muhammad_idris.Network.NetworkConfig
import com.example.muhammad_idris.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi SwipeRefreshLayout
        binding.swipeRefreshLayout.setOnRefreshListener(this)

        // Panggil metode getPosts() untuk memuat data dari server
        getPosts()

    }

    override fun onRefresh() {
        // Panggil metode getPosts() untuk memuat ulang data dari server
        getPosts()
    }

    private fun getPosts() {
        NetworkConfig().getService()
            .getmahasiswa()
            .enqueue(object : Callback<Response> {
                override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                    binding.progressIndicator.visibility = View.GONE
                    if (response.isSuccessful) {
                        val receivedDatas = response.body()?.data
                        setToAdapter(receivedDatas as List<DataItem>?)
                    }
                    binding.swipeRefreshLayout.isRefreshing = false // Beritahu bahwa proses refresh selesai
                }

                @SuppressLint("SuspiciousIndentation")
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    this@MainActivity.binding.progressIndicator.visibility = View.GONE
                    Log.d("Retrofit failed", "onFailure : ${t.stackTrace}")
                    binding.swipeRefreshLayout.isRefreshing = false // Beritahu bahwa proses refresh selesai
                }
            })
    }

    private fun setToAdapter(receivedDatas: List<DataItem>?) {
        binding.newsList.adapter = null
        val adapter = TekomAdapter(receivedDatas) {
            val detailNewsIntent = Intent(this@MainActivity, TekomAdapter::class.java)
            detailNewsIntent.putExtra("idNews", it?.gender)
            startActivity(detailNewsIntent)
        }
        val lm = LinearLayoutManager(this)
        binding.newsList.layoutManager = lm
        binding.newsList.itemAnimator = DefaultItemAnimator()
        binding.newsList.adapter = adapter
    }
}
