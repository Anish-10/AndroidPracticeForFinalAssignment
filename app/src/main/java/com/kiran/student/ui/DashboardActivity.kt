package com.kiran.student.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.kiran.student.R

class DashboardActivity : AppCompatActivity() {

    private lateinit var btnaddstudent : Button
    private lateinit var btnviewstudent : Button
    private lateinit var btnMap : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        btnaddstudent = findViewById(R.id.btnaddstudent)
        btnviewstudent = findViewById(R.id.btnviewstudent)
        btnMap = findViewById(R.id.btnMap)

        btnaddstudent.setOnClickListener{
            val intent = Intent(
                this,
                AddstudentActivity::class.java
            )
            startActivity(intent)
        }
        btnviewstudent.setOnClickListener{
            val intent = Intent(
                this,
                GetstudentActivity::class.java
            )
            startActivity(intent)
        }
    }
}