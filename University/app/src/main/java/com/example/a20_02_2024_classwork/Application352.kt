package com.example.a20_02_2024_classwork

import android.app.Application
import android.content.Context
import com.example.a20_02_2024_classwork.data.University
import com.example.a20_02_2024_classwork.repository.UniversityRepository

class Application352: Application() {

    override fun onCreate() {
        super.onCreate()
        UniversityRepository.getInstance().loadData()
    }

    init {
        instance = this
    }

    companion object {
        private var instance: Application352? = null

        val context
                get() = applicationContext()

        private fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }
}