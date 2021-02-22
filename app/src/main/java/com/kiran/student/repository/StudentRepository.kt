package com.kiran.student.repository

import com.kiran.student.api.MyApiRequest
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.api.StudentAPI
import com.kiran.student.entity.Student
import com.kiran.student.response.AddStudentResponse
import com.kiran.student.response.AllStudentsResponse
import com.kiran.student.response.DeleteStudentResponse
import com.kiran.student.response.ImageResponse
import okhttp3.MultipartBody

class StudentRepository

    : MyApiRequest() {

    private val studentAPI =
        ServiceBuilder.buildService(StudentAPI::class.java)

    //Add Student

    suspend fun addStudent(student: Student): AddStudentResponse {
        return apiRequest {
            studentAPI.addStudent(
                ServiceBuilder.token!!, student
            )
        }
    }

    suspend fun getAllStudents(): AllStudentsResponse {
        return apiRequest {
            studentAPI.getAllStudents(
                ServiceBuilder.token!!
            )
        }
    }
    suspend fun deleteStudent(student: String):DeleteStudentResponse{
        return apiRequest {
            studentAPI.deleteStudent(
                ServiceBuilder.token!!,student
            )
        }
    }

    suspend fun uploadImage(id:String, body:MultipartBody.Part):ImageResponse
    {
        return apiRequest {
            studentAPI.uploadImage(ServiceBuilder.token!!,id,body)
        }
    }
}
