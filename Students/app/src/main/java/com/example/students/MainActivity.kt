package com.example.students

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

const val KEY_MAIN = "main"
class MainActivity : AppCompatActivity() {

    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvStudentId: TextView
    private lateinit var tvCourse: TextView
    private lateinit var tvSpecialization: TextView

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


        findViewById<TextView>(R.id.first_name).text = infoStudent[0]
        findViewById<TextView>(R.id.last_name).text = infoStudent[1]
        findViewById<TextView>(R.id.student_id).text = infoStudent[2]
        findViewById<TextView>(R.id.course).text = infoStudent[3]
        findViewById<TextView>(R.id.specialization).text = infoStudent[4]

        findViewById<Button>(R.id.btnChangeStudent).setOnClickListener {
            val intent = CreateActivity.newIntent(this@MainActivity, infoStudent)
            startActivity(intent)
        }


        findViewById<Button>(R.id.btnAddStudent).setOnClickListener {
            val intent = CreateActivity.newIntent(this@MainActivity)
            startActivity(intent)
        }
    }
}