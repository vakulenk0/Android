package com.example.a20_02_2024_classwork.repository

import android.text.method.MultiTapKeyListener
import androidx.lifecycle.MutableLiveData
import com.example.a20_02_2024_classwork.data.University
import com.example.a20_02_2024_classwork.data.UniversityList

class UniversatyRepository private constructor(){
    companion object {
        private var INSTANCE : UniversatyRepository? = null

        fun getInstance(): UniversatyRepository {
            if (INSTANCE == null){
                INSTANCE = UniversatyRepository()
            }
            return INSTANCE ?:
            throw IllegalStateException("UniversityRepositor: Репозиторий не инициализирован")
        }
    }

    var universityList: MutableLiveData<UniversityList?> = MutableLiveData()
    var university: MutableLiveData<University> = MutableLiveData()

    fun newUniversity(university: University) {
        val listTmp = (universityList.value ?: UniversityList()).apply {
            items.add(university)
        }
        universityList.postValue(listTmp)
        setCurrentUniversity(university)
    }

    fun setCurrentUniversity(_university: University) {
        university.postValue(_university)
    }

    fun setCurrentUniversity(position: Int) {
        if(universityList.value == null || position < 0 || (universityList.value?.items?.size!!<=position))
            return
        setCurrentUniversity(universityList.value?.items!![position])
    }

    fun getUniversityPosition(university: University): Int = universityList.value?.items?.indexOfFirst {
        it.id == university.id } ?: -1

    fun getUniversityPosition() = getUniversityPosition(university.value?: University())

    fun updateUniversity(university: University){
        val postiton = getUniversityPosition(university)
        if (postiton < 0) newUniversity(university)
        else{
            val listTmp = universityList.value!!
            listTmp.items[postiton] = university
            universityList.postValue(listTmp)
        }
    }

    fun deleteUniversity(university: University){
        val listTmp = universityList.value!!
        if (listTmp.items.remove(university)){
            universityList.postValue(listTmp)
        }
        setCurrentUniversity(0)
    }
}