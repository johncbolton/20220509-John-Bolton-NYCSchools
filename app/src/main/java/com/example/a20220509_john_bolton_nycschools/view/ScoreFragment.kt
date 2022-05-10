package com.example.a20220509_john_bolton_nycschools.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.a20220509_john_bolton_nycschools.R
import com.example.a20220509_john_bolton_nycschools.databinding.ShowScoreFragmentBinding
import com.example.a20220509_john_bolton_nycschools.viewmodel.SchoolViewModel

class ScoreFragment: Fragment() {

    companion object {
        const val SCORE_KEY = "SCORE_KEY"
        fun newInstance(dbn: String): ScoreFragment {
            val fragment = ScoreFragment()
            val bundle = Bundle()
            bundle.putString(SCORE_KEY, dbn)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: ShowScoreFragmentBinding? = null
    private val binding : ShowScoreFragmentBinding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ShowScoreFragmentBinding.inflate(layoutInflater)
        viewModel.getThisScore(arguments?.getString(SCORE_KEY)!!)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.scoreData.observe(viewLifecycleOwner) { score ->
            if (!score.isNullOrEmpty()) {
                binding.apply {
                    scoreSchoolName.text = score[0].school_name
                    scoreMath.text = score[0].sat_math_avg_score
                    scoreReading.text = score[0].sat_critical_reading_avg_score
                    scoreWriting.text = score[0].sat_writing_avg_score
                    scoreMath.visibility = View.VISIBLE
                    scoreReading.visibility = View.VISIBLE
                    scoreWriting.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    scoreSchoolName.text = resources.getString(R.string.score_error)
                    scoreMath.visibility = View.INVISIBLE
                    scoreReading.visibility = View.INVISIBLE
                    scoreWriting.visibility = View.INVISIBLE
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.apply {
            scoreMath.visibility = View.INVISIBLE
            scoreReading.visibility = View.INVISIBLE
            scoreWriting.visibility = View.INVISIBLE
        }
        _binding = null
    }
}