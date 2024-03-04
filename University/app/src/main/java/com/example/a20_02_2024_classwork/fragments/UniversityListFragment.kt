package com.example.a20_02_2024_classwork.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20_02_2024_classwork.R
import com.example.a20_02_2024_classwork.databinding.FragmentUniversityListBinding

class UniversityListFragment : Fragment() {

    companion object {
        private var INSTANCE: UniversityListFragment? = null

        fun getInstance(): UniversityListFragment {
            if (INSTANCE == null) INSTANCE = UniversityListFragment()
            return INSTANCE ?: throw Exception("UniversityFragment не создан")
        }
    }

    private lateinit var viewModel: UniversityListViewModel
    private lateinit var _binding : FragmentUniversityListBinding
    val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUniversityListBinding.inflate(inflater, container, false)

        binding.rvUniversityList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
    

}