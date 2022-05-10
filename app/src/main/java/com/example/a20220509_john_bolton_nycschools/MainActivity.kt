package com.example.a20220509_john_bolton_nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a20220509_john_bolton_nycschools.databinding.ActivityMainBinding
import com.example.a20220509_john_bolton_nycschools.viewmodel.ViewModelHelper

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelHelper.createViewModel(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getAllSchools()
    }
}