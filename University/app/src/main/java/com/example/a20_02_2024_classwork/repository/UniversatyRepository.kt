package com.example.a20_02_2024_classwork.repository

import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.a20_02_2024_classwork.Application352
import com.example.a20_02_2024_classwork.R
import com.example.a20_02_2024_classwork.data.University
import com.example.a20_02_2024_classwork.data.UniversityList
import com.example.a20_02_2024_classwork.data.Faculty
import com.example.a20_02_2024_classwork.data.FacultyList
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken //Возможно, не нужно
import com.google.gson.Gson

const val TAG = "com.example.a20_02_2024_classwork_TAG"

class UniversityRepository private constructor() {
    companion object {
        private var INSTANCE: UniversityRepository? = null

        fun getInstance(): UniversityRepository {
            if (INSTANCE == null) {
                INSTANCE = UniversityRepository()
            }
            return INSTANCE
                ?: throw IllegalStateException("UniversityRepository: Репозиторий не инициализирован")
        }
    }

    var universityList: MutableLiveData<UniversityList?> = MutableLiveData()
    var university: MutableLiveData<University> = MutableLiveData()

    fun newUniversity(university: University) {
        var listTmp = (universityList.value ?: UniversityList()).apply {
            items.add(university)
        }
        universityList.postValue(listTmp)
        setCurrentUniversity(university)
    }

    fun setCurrentUniversity(_university: University) {
        university.postValue(_university)
    }

    fun setCurrentUniversity(position: Int) {
        if (universityList.value == null || position < 0 || (universityList.value?.items?.size!! <= position))
            return
        setCurrentUniversity(universityList.value?.items!![position])
    }

    fun getUniversityPosition(university: University): Int =
        universityList.value?.items?.indexOfFirst {
            it.id == university.id
        } ?: 1

    fun getUniversityPosition() = getUniversityPosition(university.value ?: University())

    fun updateUniversity(university: University) {
        val position = getUniversityPosition(university)
        if (position < 0) newUniversity(university)
        else {
            val listTmp = universityList.value!!
            listTmp.items[position] = university
            universityList.postValue(listTmp)
        }
    }

    fun deleteUniversity(university: University) {
        val listTmp = universityList.value!!
        if (listTmp.items.remove(university)) {
            universityList.postValue(listTmp)
        }
        setCurrentUniversity(0)
    }

    fun saveData() {
        val context = Application352.context
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().apply() {
            val gson = Gson()
            val lst = universityList.value?.items ?: listOf<University>()
            val jsonString = gson.toJson(lst)
            Log.d(TAG, "Сохранение $jsonString")
            putString(context.getString(R.string.preference_key_university_list), jsonString)
            putString(
                context.getString(R.string.preference_key_university_list),
                gson.toJson(facultyList.value?.items ?: listOf<Faculty>())
            )
            apply()
        }

    }

    fun loadData() {
        val context = Application352.context
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.apply {
            var jsonString =
                getString(context.getString(R.string.preference_key_university_list), null)
            if (jsonString != null) {
                Log.d(TAG, "Чтение University $jsonString")
                val listType = object : TypeToken<List<University>>() {}.type
                val tempList = Gson().fromJson<List<University>>(jsonString, listType)
                val temp = UniversityList()
                temp.items = tempList.toMutableList()
                Log.d(TAG, "Загрузка University ${temp.toString()}")
                universityList.value = temp
                setCurrentUniversity(0)
            }

            jsonString =
                getString(context.getString(R.string.preference_key_university_list), null)
            if (jsonString != null) {
                Log.d(TAG, "Чтение Faculty $jsonString")
                val listType = object : TypeToken<List<Faculty>>() {}.type
                val tempList = Gson().fromJson<List<Faculty>>(jsonString, listType)
                val temp = FacultyList()
                temp.items = tempList.toMutableList()
                Log.d(TAG, "Загрузка Faculty ${temp.toString()}")
                facultyList.value = temp
                setCurrentFaculty(0)
            }
        }

    }


    var facultyList: MutableLiveData<FacultyList?> = MutableLiveData()
    var faculty: MutableLiveData<Faculty> = MutableLiveData()

    fun newFaculty(faculty: Faculty) {
        var listTmp = (facultyList.value ?: FacultyList()).apply {
            items.add(faculty)
        }
        facultyList.postValue(listTmp)
        setCurrentFaculty(faculty)
    }

    fun setCurrentFaculty(_faculty: Faculty) {
        faculty.postValue(_faculty)
    }

    fun setCurrentFaculty(position: Int) {
        if (facultyList.value == null || position < 0 || (facultyList.value?.items?.size!! <= position))
            return
        setCurrentFaculty(facultyList.value?.items!![position])
    }

    fun getFacultyPosition(faculty: Faculty): Int = facultyList.value?.items?.indexOfFirst {
        it.id == faculty.id
    } ?: 1

    fun getFacultyPosition() = getFacultyPosition(faculty.value ?: Faculty())

    fun updateFaculty(faculty: Faculty) {
        val position = getFacultyPosition(faculty)
        if (position < 0) newFaculty(faculty)
        else {
            val listTmp = facultyList.value!!
            listTmp.items[position] = faculty
            facultyList.postValue(listTmp)
        }
    }

    fun deleteFaculty(faculty: Faculty) {
        val listTmp = facultyList.value!!
        if (listTmp.items.remove(faculty)) {
            facultyList.postValue(listTmp)
        }
        setCurrentFaculty(0)
    }

}