package com.example.students

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val KEY = "Info"

class ChangeActivity : AppCompatActivity() {

    private lateinit var pvFirstName: TextView
    private lateinit var pvLastName: TextView
    private lateinit var pvIdStudent: TextView
    private lateinit var pvCourse: TextView
    private lateinit var pvSpecialization: TextView
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    companion object {
        fun newIntent(packageContext: Context, studentInfo: ArrayList<String?>?): Intent {
            return Intent(packageContext, ChangeActivity::class.java).apply {
                putExtra(KEY, studentInfo)
            }
        }
    }

    private val studentVM: StudentViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(StudentViewModel::class.java)
    }

    private lateinit var studentInfo: ArrayList<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change)

        studentVM.print()

        studentInfo = intent?.getStringArrayListExtra(KEY) ?: arrayListOf("", "", "", "", "")

        pvFirstName = findViewById(R.id.first_name_change)
        pvLastName = findViewById(R.id.last_name_change)
        pvIdStudent = findViewById(R.id.student_id_change)
        pvCourse = findViewById(R.id.course_change)
        pvSpecialization = findViewById(R.id.specialization_change)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        pvFirstName.text = studentInfo[0]
        pvLastName.text = studentInfo[1]
        pvIdStudent.text = studentInfo[2]
        pvCourse.text = studentInfo[3]
        pvSpecialization.text = studentInfo[4]

        btnCancel.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            studentInfo[0] = pvFirstName.text.toString()
            studentInfo[1] = pvLastName.text.toString()
            studentInfo[2] = pvIdStudent.text.toString()
            studentInfo[3] = pvCourse.text.toString()
            studentInfo[4] = pvSpecialization.text.toString()

            val intent = MainActivity.newIntent(this@ChangeActivity, studentInfo)
            startActivity(intent)
        }


    }
}