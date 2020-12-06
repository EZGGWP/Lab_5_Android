package com.itmo.lab5belov

import android.content.Context
import android.system.Os.read
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import okhttp3.OkHttpClient
//import okhttp3.Callback
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class Loader(val parent: Context, val adapter: Adapter, val loading: ProgressBar) {
    lateinit var dataSet: ResponseJson;
    val baseUrl = URL("https://api.pexels.com/v1/");
    ///search?query=jdm
    val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl).build();
    val client = retrofit.create(PexelsApi::class.java);
    init {
        loadPics("Kinkaku");

    }

    fun loadPics(query: String) {
        adapter.removeAllPics();
        val call = client.getPics(query).enqueue(object :  Callback<ResponseJson> {
            override fun onFailure(call: Call<ResponseJson>, t: Throwable) {
                Toast.makeText(parent, "There was an error.", Toast.LENGTH_SHORT).show();
                loading.isVisible = false;
                t.printStackTrace();
            }

            override fun onResponse(call: Call<ResponseJson>, response: Response<ResponseJson>) {
                if (response.body() != null) {
                    Log.d("DEBUGGING", response.body()!!.total_results.toString())
                    dataSet = response.body()!!;
                    loading.isVisible = false;
                    adapter.addPics(dataSet.photos);
                    Log.d("DEBUGGGING", dataSet.photos[0].id)
                } else {
                    Toast.makeText(parent, "No images found...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}