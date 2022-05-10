package com.example.a20220509_john_bolton_nycschools.api

import com.example.a20220509_john_bolton_nycschools.model.School
import com.example.a20220509_john_bolton_nycschools.model.Score
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {

    @GET(SCHOOL_ENDPOINT)
    suspend fun getAllSchools(): Response<List<School>>

    @GET(SCORE_ENDPOINT)
    suspend fun getThisScore(
        @Query("dbn") dbn: String
    ): Response<List<Score>>


    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOL_ENDPOINT = "s3k6-pzi2.json"
        const val SCORE_ENDPOINT = "f9bf-2cp4.json"

        var instance: Retrofit? = null
        fun createRetrofitInstance(): Retrofit {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return instance!!
        }
    }
}