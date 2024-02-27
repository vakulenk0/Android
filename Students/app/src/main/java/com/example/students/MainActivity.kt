package com.example.students

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val KEY_MAIN = "main"
class MainActivity : AppCompatActivity() {

    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvStudentId: TextView
    private lateinit var tvCourse: TextView
    private lateinit var tvSpecialization: TextView

    private val studentVM: StudentViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(StudentViewModel::class.java)
    }

    companion object {
        fun newIntent(packageContext: Context, list: ArrayList<String?>): Intent {
            return Intent(packageContext, MainActivity::class.java).apply {
                putExtra(KEY_MAIN, list)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val infoStudent = intent?.getStringArrayListExtra(KEY_MAIN) ?: arrayListOf<String?>("", "", "", "", "")

        studentVM.changeFirstName(infoStudent[0])
        studentVM.changeLastName(infoStudent[1])
        studentVM.changeStudentId(infoStudent[2])
        studentVM.changeCourse(infoStudent[3])
        studentVM.changeSpecialization(infoStudent[4])
        studentVM.print()


        findViewById<TextView>(R.id.first_name).text = studentVM.studentFirstName
        findViewById<TextView>(R.id.last_name).text = studentVM.studentLastName
        findViewById<TextView>(R.id.student_id).text = studentVM.studentId
        findViewById<TextView>(R.id.course).text = studentVM.studentCourse
        findViewById<TextView>(R.id.specialization).text = studentVM.studentSpecialization

        findViewById<Button>(R.id.btnChangeStudent).setOnClickListener {
            val intent = ChangeActivity.newIntent(this@MainActivity, infoStudent)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAddStudent).setOnClickListener {
            val intent = CreateActivity.newIntent(this@MainActivity)
            startActivity(intent)
        }
    }
}