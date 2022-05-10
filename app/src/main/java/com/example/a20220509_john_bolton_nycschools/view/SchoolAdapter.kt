package com.example.a20220509_john_bolton_nycschools.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a20220509_john_bolton_nycschools.databinding.SchoolListItemBinding
import com.example.a20220509_john_bolton_nycschools.model.School

class SchoolAdapter(
    private val schools: List<School>,
    private val showDetails: (String) -> Unit
): RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {
    inner class SchoolViewHolder(private val binding: SchoolListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(school: School) {
                binding.apply {
                    listSchoolName.text = school.school_name
                    listSchoolWebsite.text = school.website
                    listButtonScore.setOnClickListener {
                        showDetails(school.dbn)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        return SchoolViewHolder(
            SchoolListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(schools[position])
    }

    override fun getItemCount(): Int {
        return schools.size
    }
}