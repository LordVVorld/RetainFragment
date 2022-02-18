package com.example.retainfragment


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.rView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter()
        var mService = Common.getRetrofitServices(LoggingInterceptorClient.client)
        getAllWeatherList(recyclerView.adapter as Adapter, mService)
        mService = Common.getRetrofitServices(HttpLoggingInterceptorClient.client)
        getAllWeatherList(recyclerView.adapter as Adapter, mService)
    }

    private fun getAllWeatherList(adapter: Adapter, mService: RetrofitServices) {
        mService.getWeatherList().enqueue(object : Callback<DataWeather> {
            override fun onResponse(call: Call<DataWeather>, response: Response<DataWeather>) {
                val dataWeather = response.body() as DataWeather
                val listWeather = dataWeather.list
                adapter.submitList(listWeather)
            }

            override fun onFailure(call: Call<DataWeather>, t: Throwable) {
            }
        })

    }
}