package com.example.a20_02_2024_classwork.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.a20_02_2024_classwork.data.University
import com.example.a20_02_2024_classwork.data.UniversityList
import com.example.a20_02_2024_classwork.repository.UniversatyRepository

class UniversityListViewModel : ViewModel() {
    var universityList: MutableLiveData<UniversityList> = MutableLiveData()

    private var _university: University? = null

    val university
        get() = _university

    private val universityListObserver = Observer<UniversityList?> {
        list ->
        universityList.postValue(list)
    }

    init {
        UniversatyRepository.getInstance().universityList.observeForever(universityListObserver)
        UniversatyRepository.getInstance().university.observeForever {
            _university = it
        }

        fun deleteUniversity(){
            if(university!=null)
                UniversatyRepository.getInstance().deleteUniversity(university!!)
        }

        fun appendUniversity(universityName: String, universityCity: String){
            val university = University()
            university.name = universityName
            university.city = universityCity
            UniversatyRepository.getInstance().newUniversity(university)
        }

        fun updateUniversity(universityName: String, universityCity: String) {
            if(_university!=null){
                _university!!.name = universityName
                _university!!.city = universityCity
                UniversatyRepository.getInstance().updateUniversity(_university!!)
            }
        }

        fun setCurrentUniversity(university: University){
            UniversatyRepository.getInstance().setCurrentUniversity(university)
        }
    }
}