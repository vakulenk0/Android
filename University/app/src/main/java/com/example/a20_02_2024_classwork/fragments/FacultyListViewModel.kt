package com.example.a20_02_2024_classwork.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.a20_02_2024_classwork.data.Faculty
import com.example.a20_02_2024_classwork.data.FacultyList
import com.example.a20_02_2024_classwork.repository.UniversityRepository

class FacultyListViewModel : ViewModel() {
    var facultyList: MutableLiveData<FacultyList?> = MutableLiveData()

    private var _faculty: Faculty? = null

    val faculty
        get() = _faculty

    private val facultyListObserver = Observer<FacultyList?>{
            list ->
        facultyList.postValue(list)
    }

    init{
        UniversityRepository.getInstance().facultyList.observeForever(facultyListObserver)
        UniversityRepository.getInstance().faculty.observeForever{
            _faculty = it
        }
    }

    fun deleteFaculty(){
        if(faculty != null){
            UniversityRepository.getInstance().deleteFaculty(faculty!!)
        }
    }

    fun appendFaculty(name: String, city: String){
        val faculty = Faculty()
        faculty.name = name
        UniversityRepository.getInstance().newFaculty(faculty)
    }

    fun updateFaculty(name: String, city: String){
        if (_faculty != null){
            _faculty!!.name = name
            UniversityRepository.getInstance().updateFaculty(_faculty!!)
        }
    }

    fun setCurrentFaculty(faculty: Faculty){
        UniversityRepository.getInstance().setCurrentFaculty(faculty)
    }
}