package com.example.students

import android.util.Log
import androidx.lifecycle.ViewModel
import java.io.Console

const val LOG_TAG = "debug"

class StudentViewModel: ViewModel() {
    private val studentInfo = arrayListOf<String?>(
        "",
        "",
        "",
        "",
        ""
    )
    

    val studentFirstName: String?
        get() = studentInfo[0]
    val studentLastName: String?
        get() = studentInfo[1]
    val studentId: String?
        get() = studentInfo[2]
    val studentCourse: String?
        get() = studentInfo[3]
    val studentSpecialization: String?
        get() = studentInfo[4]

    fun changeFirstName(fn: String?) {
        studentInfo[0] = fn
    }
    fun changeLastName(ln: String?) {
        studentInfo[1] = ln
    }
    fun changeStudentId(si: String?) {
        studentInfo[2] = si
    }
    fun changeCourse(cour: String?) {
        studentInfo[3] = cour
    }
    fun changeSpecialization(spec: String?) {
        studentInfo[4] = spec
    }

    fun print () {
        for (i in studentInfo) {
            Log.d(LOG_TAG, i.toString())
        }
    }

}