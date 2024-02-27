package com.example.a20_02_2024_classwork.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a20_02_2024_classwork.R

class UniversityListFragment : Fragment() {

    companion object {
        fun newInstance() = UniversityListFragment()
    }

    private lateinit var viewModel: UniversityListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_university_list, container, false)
    }
    

}