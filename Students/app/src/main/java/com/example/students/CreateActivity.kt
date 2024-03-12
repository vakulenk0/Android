package com.example.students

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val LOG = "debug"
const val KEY = "Info"
const val KEY_BACK = "array"
class CreateActivity : AppCompatActivity() {

    private lateinit var pvFirstName: TextView
    private lateinit var pvLastName: TextView
    private lateinit var pvIdStudent: TextView
    private lateinit var pvCourse: TextView
    private lateinit var pvSpecialization: TextView
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    companion object {
        fun newIntent(packageContext: Context, studentInfo: ArrayList<String?>): Intent {
            return Intent(packageContext, CreateActivity::class.java).apply {
                putExtra(KEY, studentInfo)
            }
        }

        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, CreateActivity::class.java)
        }
    }

    fun isValidText(name: String): Boolean {
        val regex = """[a-zA-Z]+""".toRegex()
        return regex.matches(name)
    }

    fun isValidNumber(name: String): Boolean {
        val regex = """[0-9]+""".toRegex()
        return regex.matches(name)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        pvFirstName = findViewById(R.id.tvFN)
        pvLastName = findViewById(R.id.tvLN)
        pvIdStudent = findViewById(R.id.tvId)
        pvCourse = findViewById(R.id.tvCour)
        pvSpecialization = findViewById(R.id.tvSpec)
        btnSave = findViewById(R.id.btnSaveCr)
        btnCancel = findViewById(R.id.btnCancelCr)

        val studentInfo: ArrayList<String?> = intent?.getStringArrayListExtra(KEY) ?: arrayListOf("", "", "", "", "")

        pvFirstName.text = studentInfo[0]
        pvLastName.text = studentInfo[1]
        pvIdStudent.text = studentInfo[2]
        pvCourse.text = studentInfo[3]
        pvSpecialization.text = studentInfo[4]


        btnCancel.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            if (!isValidText(pvFirstName.text.toString())) {
                pvFirstName.error = "Некорректное имя"
                return@setOnClickListener
            }

            if (!isValidText(pvLastName.text.toString())) {
                pvLastName.error = "Некорректная фамилия"
                return@setOnClickListener
            }

            if (!isValidNumber(pvIdStudent.text.toString())) {
                pvIdStudent.error = "Некорректный Id"
                return@setOnClickListener
            }

            if (!isValidNumber(pvCourse.text.toString())) {
                pvCourse.error = "Некорректный курс"
                return@setOnClickListener
            }

            if (!isValidText(pvSpecialization.text.toString())) {
                pvSpecialization.error = "Некорректная специальность"
                return@setOnClickListener
            }

            pvFirstName.error = null
            pvLastName.error = null
            pvIdStudent.error = null
            pvCourse.error = null
            pvSpecialization.error = null

            studentInfo[0] = pvFirstName.text.toString()
            studentInfo[1] = pvLastName.text.toString()
            studentInfo[2] = pvIdStudent.text.toString()
            studentInfo[3] = pvCourse.text.toString()
            studentInfo[4] = pvSpecialization.text.toString()

            val data = Intent().apply {
                putExtra(KEY_BACK, studentInfo)
            }

            setResult(Activity.RESULT_OK, data)
            finish()
        }



    }
}