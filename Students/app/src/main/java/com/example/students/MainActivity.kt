package com.example.students

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider

const val KEY_MAIN = "main"
class MainActivity : AppCompatActivity() {

    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvStudentId: TextView
    private lateinit var tvCourse: TextView
    private lateinit var tvSpecialization: TextView
    private lateinit var infoStudent: ArrayList<String?>
    private lateinit var btnChange: Button
    private lateinit var btnAdd: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvFirstName = findViewById<TextView>(R.id.first_name)
        tvLastName = findViewById<TextView>(R.id.last_name)
        tvStudentId = findViewById<TextView>(R.id.student_id)
        tvCourse = findViewById<TextView>(R.id.course)
        tvSpecialization = findViewById<TextView>(R.id.specialization)
        btnChange = findViewById<Button>(R.id.btnChangeStudent)
        btnAdd = findViewById<Button>(R.id.btnAddStudent)


        val resultLauncher1 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data : Intent? = result.data
                infoStudent = data?.getStringArrayListExtra(KEY_BACK) ?: arrayListOf("", "", "", "", "")
                tvFirstName.text = infoStudent[0]?.replace(Regex("[^а-яА-Яa-zA-Z]"), "")
                tvLastName.text = infoStudent[1]?.replace(Regex("[^а-яА-Яa-zA-Z]"), "")
                tvStudentId.text = infoStudent[2]
                tvCourse.text = infoStudent[3]
                tvSpecialization.text = infoStudent[4]?.replace(Regex("[^а-яА-Яa-zA-Z]"), "")
            }
        }

        infoStudent = arrayListOf("", "", "", "", "")

        btnAdd.setOnClickListener {
            val intent = CreateActivity.newIntent(this@MainActivity)
            resultLauncher1.launch(intent)
        }

        val resultLauncher2 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data : Intent? = result.data
                infoStudent = data?.getStringArrayListExtra(KEY_BACK) ?: arrayListOf("", "", "", "", "")

                tvFirstName.text = infoStudent[0]?.replace(Regex("[^а-яА-Яa-zA-Z]"), "")
                tvLastName.text = infoStudent[1]?.replace(Regex("[^а-яА-Яa-zA-Z]"), "")
                tvStudentId.text = infoStudent[2]
                tvCourse.text = infoStudent[3]
                tvSpecialization.text = infoStudent[4]?.replace(Regex("[^а-яА-Яa-zA-Z]"), "")
            }
        }

        btnChange.setOnClickListener {
            if (tvFirstName.text != "" || tvLastName.text != "" || tvStudentId.text != "" ||
            tvCourse.text != "" || tvSpecialization.text != "") {
                val intent = CreateActivity.newIntent(this@MainActivity, infoStudent)
                resultLauncher2.launch(intent)
            }
            else {
                Toast.makeText(this, "Создайте студента", Toast.LENGTH_SHORT).show()
            }

        }

        fun checkString(r: String?) {

        }
    }
}