package com.example.students

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

        Log.d(LOG_TAG, "SecondActivity")
        Log.d(LOG, studentInfo.toString())

        if (pvFirstName.text != "" || pvLastName.text != "" || pvIdStudent.text != "" ||
            pvCourse.text != "" || pvSpecialization.text != ""){
            pvFirstName.text = studentInfo[0]
            pvLastName.text = studentInfo[1]
            pvIdStudent.text = studentInfo[2]
            pvCourse.text = studentInfo[3]
            pvSpecialization.text = studentInfo[4]

        }

        btnCancel.setOnClickListener {
            finish()
        }



        btnSave.setOnClickListener {
            studentInfo[0] = pvFirstName.text.toString()
            studentInfo[1] = pvLastName.text.toString()
            studentInfo[2] = pvIdStudent.text.toString()
            studentInfo[3] = pvCourse.text.toString()
            studentInfo[4] = pvSpecialization.text.toString()

            val intent = MainActivity.newIntent(this@CreateActivity, studentInfo)
            startActivity(intent)
        }

    }
}