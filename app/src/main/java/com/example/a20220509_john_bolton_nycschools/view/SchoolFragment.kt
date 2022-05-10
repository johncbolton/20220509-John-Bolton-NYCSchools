package com.example.a20220509_john_bolton_nycschools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20220509_john_bolton_nycschools.R
import com.example.a20220509_john_bolton_nycschools.databinding.SchoolRecyclerViewBinding
import com.example.a20220509_john_bolton_nycschools.viewmodel.SchoolViewModel

class SchoolFragment : Fragment() {
    lateinit var binding: SchoolRecyclerViewBinding
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SchoolRecyclerViewBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.schoolData.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.apply {
                    schoolList.adapter = SchoolAdapter(it, ::showDetails)
                    schoolList.layoutManager = LinearLayoutManager(context)
                }
            } else {
                binding.apply {
                    schoolErrorText.text = resources.getString(R.string.school_error)
                    schoolErrorText.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showDetails(dbn: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container_for_fragments, ScoreFragment.newInstance(dbn))
            .addToBackStack(null)
            .commit()
    }
}