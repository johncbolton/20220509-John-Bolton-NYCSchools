package com.example.a20220509_john_bolton_nycschools.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.a20220509_john_bolton_nycschools.api.SchoolService

object ViewModelHelper {

    fun createService(): SchoolService =
        SchoolService.createRetrofitInstance().create(SchoolService::class.java)

    fun createViewModel(owner: ViewModelStoreOwner) =
        ViewModelProvider(owner, object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SchoolViewModel(createService()) as T
            }
        })[SchoolViewModel::class.java]
}