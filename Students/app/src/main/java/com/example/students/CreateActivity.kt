package com.example.students

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class CreateActivity : AppCompatActivity() {

    private lateinit var pvFirstName: TextView
    private lateinit var pvLastName: TextView
    private lateinit var pvIdStudent: TextView
    private lateinit var pvCourse: TextView
    private lateinit var pvSpecialization: TextView
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, CreateActivity::class.java)
        }
    }

    private val studentVM: StudentViewModel by lazy {
        val provider = ViewModelProvider(this)
        provider.get(StudentViewModel::class.java)
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


        btnCancel.setOnClickListener {
            finish()
        }

        var infoStudent = arrayListOf<String?>("", "", "", "", "")


        btnSave.setOnClickListener {
            infoStudent[0] = pvFirstName.text.toString()
            infoStudent[1] = pvLastName.text.toString()
            infoStudent[2] = pvIdStudent.text.toString()
            infoStudent[3] = pvCourse.text.toString()
            infoStudent[4] = pvSpecialization.text.toString()

            val intent = MainActivity.newIntent(this@CreateActivity, infoStudent)
            startActivity(intent)
        }
    }
}