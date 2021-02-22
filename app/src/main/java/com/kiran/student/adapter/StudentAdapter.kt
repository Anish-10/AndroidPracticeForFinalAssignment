package com.kiran.student.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kiran.student.R
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.entity.Student
import com.kiran.student.repository.StudentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class StudentAdapter (
    private val context : Context,
    private val lstStudent :MutableList<Student>

): RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var photo : ImageView = view.findViewById(R.id.photo)
        val tvname: TextView = view.findViewById(R.id.tvname)
        val tvage: TextView = view.findViewById(R.id.tvage)
        val tvgender: TextView = view.findViewById(R.id.tvgender)
        val tvaddress: TextView = view.findViewById(R.id.tvaddress)
        val delete : ImageView = view.findViewById(R.id.delete)
        val edit : ImageView = view.findViewById(R.id.edit)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewstudent, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {
        val student = lstStudent[position]
        holder.tvname.text = student.fullname
        holder.tvage.text = student.age.toString()
        holder.tvgender.text = student.gender
        holder.tvaddress.text = student.address

        val imagePath = ServiceBuilder.loadImagePath() + student.photo
        if (!student.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.photo)
        }

//        holder.edit.setOnClickListener {
//            val intent = Intent(context,UpdateActivity::class.java)
//            intent.putExtra("student",student)
//            context.startActivity(intent)
//        }

        holder.delete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Student")
            builder.setMessage("Are You Sure You Want To Delete  ${student.fullname} ?")
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton("Yes") { _, _ ->

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val studentRepository = StudentRepository()
                        val response = studentRepository.deleteStudent(student._id !!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Student Deleted",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                        withContext(Main) {
                            lstStudent.remove(student)
                            notifyDataSetChanged()
                        }

                    }catch (ex:Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,
                                ex.toString(),
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                        }
                    }


                builder.setNegativeButton("No") { _, _ ->
                    Toast.makeText(context, "Action Cancelled", Toast.LENGTH_SHORT).show()
                }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        }
    override fun getItemCount(): Int {
            return lstStudent.size
        }

    }