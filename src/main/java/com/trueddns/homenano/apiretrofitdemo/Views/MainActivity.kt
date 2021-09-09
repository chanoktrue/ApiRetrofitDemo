package com.trueddns.homenano.apiretrofitdemo.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.trueddns.homenano.apiretrofitdemo.Models.Album
import com.trueddns.homenano.apiretrofitdemo.R
import com.trueddns.homenano.apiretrofitdemo.Viewmodels.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit private var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val apiService = ApiService.retrofitBuild()
        apiService = ApiService()
//        getAlbums()
//        getAlbum(3 )
//        getFromUserId(1)
        getFromUserIdByMap()
    }

    private fun getFromUserIdByMap() {
        val hasMap = hashMapOf<String, String>()
        hasMap.put("userId", "3")
        hasMap.put("id", "23")

        val call = apiService.getFromUserIdByMap(hasMap)

        call.enqueue(
            object : Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    if (response.isSuccessful) {
                        val lists = response.body()
                        Log.e("Api", "${lists!!}")
                    }
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    Log.e("Api", t.message.toString())
                }
            }

        )

    }

    private fun getFromUserId(userId: Int) {
        val call = apiService.getFromUserId(userId)

        call.enqueue(
            object : Callback<List<Album>> {
                override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                    if (response.isSuccessful) {
                        val lists = response.body()
                        Log.e("Api", "${lists!!.size}")
                    }
                }

                override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                    Log.e("API", t.message.toString())
                }
            }
        )
    }

    private fun getAlbum(no: Int) {
        val call = apiService.getAlbum(no)

        call.enqueue(
            object : Callback<Album> {
                override fun onResponse(call: Call<Album>, response: Response<Album>) {

                    if (response.isSuccessful) {
                        val item = response.body()
                            Log.e("API", item.toString())
                    }

                }

                override fun onFailure(call: Call<Album>, t: Throwable) {
                    Log.e("API", t.message.toString())
                }
            }
        )
    }


    private fun getAlbums() {
        val call = apiService.getAlbums()

        call.enqueue(
            object : Callback<List<Album>> {
            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful) {
                    val list = response.body()
                    Log.e("API", "${ list!!.size }")


                    for (i in 0 until list!!.size) {
                        val msg = "id: ${list[i].id}\n userId: ${list[i].userId}\n title: ${list[i].title}\n"
                        Log.e("Result", msg)
                    }
                }
            }

            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.e("API", t.message.toString())
            }
        }
        )
    }

}
