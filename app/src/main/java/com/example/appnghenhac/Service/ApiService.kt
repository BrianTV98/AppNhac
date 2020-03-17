package com.example.appnghenhac.Service

import retrofit2.create

class ApiService {
    companion object{
        val base_url="https://homeward-bands.000webhostapp.com/Server/"
        fun getService():DataService{
            return ApiRetrofitClient.getClent(base_url).create(DataService::class.java)
        }
    }
}