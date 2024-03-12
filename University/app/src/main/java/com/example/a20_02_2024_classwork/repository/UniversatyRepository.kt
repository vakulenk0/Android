package com.example.a20_02_2024_classwork.repository

import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.a20_02_2024_classwork.Application352
import com.example.a20_02_2024_classwork.R
import com.example.a20_02_2024_classwork.data.University
import com.example.a20_02_2024_classwork.data.UniversityList
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken //Возможно, не нужно
import com.google.gson.Gson


const val TAG = "com.example.a20_02_2024_classwork_TAG"

class UniversityRepository private constructor(){
    companion object{
        private var INSTANCE: UniversityRepository?=null

        fun getInstance(): UniversityRepository{
            if(INSTANCE == null){
                INSTANCE = UniversityRepository()
            }
            return INSTANCE?:
            throw IllegalStateException("UniversityRepository: Репозиторий не инициализирован")
        }
    }

    var universityList: MutableLiveData<UniversityList?> = MutableLiveData()
    var university: MutableLiveData<University> = MutableLiveData()

    fun newUniversity(university: University){
        var listTmp = (universityList.value ?: UniversityList()).apply {
            items.add(university)
        }
        universityList.postValue(listTmp)
        setCurrentUniversity(university)
    }

    fun setCurrentUniversity(_university: University){
        university.postValue(_university)
    }
    fun setCurrentUniversity(position: Int){
        if (universityList.value == null || position < 0  || (universityList.value?.items?.size!! <= position))
            return
        setCurrentUniversity(universityList.value?.items!![position])
    }

    fun getUniversityPosition(university: University):Int = universityList.value?.items?.indexOfFirst{
        it.id==university.id} ?: 1

    fun getUniversityPosition()=getUniversityPosition(university.value?: University())

    fun updateUniversity(university: University){
        val position = getUniversityPosition(university)
        if(position < 0) newUniversity(university)
        else{
            val listTmp = universityList.value!!
            listTmp.items[position] = university
            universityList.postValue(listTmp)
        }
    }

    fun deleteUniversity(university: University){
        val listTmp = universityList.value!!
        if(listTmp.items.remove(university)){
            universityList.postValue(listTmp)
        }
        setCurrentUniversity(0)
    }

    fun saveData() {
        val context = Application352.context
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().apply {
            val gson = Gson()
            val lst = universityList.value?.items ?: listOf<University>()
            val jsonString = gson.toJson(lst)
            Log.d(TAG, "Сохранение $jsonString")
            putString(context.getString(R.string.preference_key_university_list),
                jsonString)
            apply()
        }
    }

    fun loadData() {
        val context = Application352.context
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.apply {
            val jsonString = getString(context.getString(R.string.preference_key_university_list), null)
            if (jsonString!= null) {
                Log.d(TAG, "Чтение $jsonString")
                val listType = object : TypeToken<List<University>>() {}.type
                val tempList = Gson().fromJson<List<University>>(jsonString, listType)
                val temp = UniversityList()
                temp.items = tempList.toMutableList()
                Log.d(TAG, "Загрузка ${temp.toString()}")
                universityList.postValue(temp)
            }
        }
    }
}