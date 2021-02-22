package com.kiran.student.response

import com.kiran.student.entity.Student

data class AllStudentsResponse(
    val success : Boolean?=true,
    val count : Int? = 0,
    val data : MutableList<Student>? = null
)