package com.example.a20220509_john_bolton_nycschools.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a20220509_john_bolton_nycschools.api.SchoolService
import com.example.a20220509_john_bolton_nycschools.model.School
import com.example.a20220509_john_bolton_nycschools.model.Score
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SchoolViewModel(private val service: SchoolService): ViewModel() {

    private val _schoolData = MutableLiveData<List<School>>()
    val schoolData: LiveData<List<School>> get() = _schoolData

    private val _scoreData = MutableLiveData<List<Score>>()
    val scoreData: LiveData<List<Score>> get() = _scoreData

    fun getAllSchools() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getAllSchools()
            if (response.isSuccessful) {
                _schoolData.postValue(response.body()!!)
            } else {
                _schoolData.postValue(emptyList())
            }
        }
    }

    fun getThisScore(dbn: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getThisScore(dbn)
            if (response.isSuccessful) {
                _scoreData.postValue(response.body()!!)
            } else {
                _scoreData.postValue(emptyList())
            }
        }
    }
}