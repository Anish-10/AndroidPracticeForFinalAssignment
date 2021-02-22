package com.kiran.student.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiran.student.R
import com.kiran.student.adapter.StudentAdapter
import com.kiran.student.entity.Student
import com.kiran.student.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GetstudentActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstudent)

        recyclerView = findViewById(R.id.recyclerview)

        loadStudents()
    }

    private fun loadStudents(){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val studentRepository = StudentRepository()
                val response = studentRepository.getAllStudents()
                if (response.success == true){
                    val lstStudent = response.data
                    withContext(Dispatchers.Main){
                        recyclerView.adapter = StudentAdapter(this@GetstudentActivity,lstStudent!!)
                        recyclerView.layoutManager = LinearLayoutManager(this@GetstudentActivity)
                    }
                }
            }catch (ex:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@GetstudentActivity,
                        "Error : ${ex.toString()}",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}