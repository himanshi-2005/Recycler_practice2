package com.example.recycler_practice2.Recycler

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler_practice2.R
import com.example.recycler_practice2.databinding.ActivityRecyclerViewBinding

class Recycler_view : AppCompatActivity(), Recycler_adapter.onClick {
    lateinit var binding: ActivityRecyclerViewBinding
    lateinit var recyclerAdapter: Recycler_adapter
    var studentlist = arrayListOf<Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        studentlist.add(Student(1,"Rohan","science"))
        studentlist.add(Student(2,"Mohan","math"))
        studentlist.add(Student(3,"sahil","chemistry"))

        recyclerAdapter= Recycler_adapter(studentlist,this)
        binding.recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recycler.adapter=recyclerAdapter

    }

    override fun delete(position: Int) {

        studentlist.removeAt(position)
        recyclerAdapter.notifyDataSetChanged()
        Toast.makeText(this, "delete :${studentlist[position]}", Toast.LENGTH_SHORT).show()
    }

    override fun update(position: Int) {
        var dialog= Dialog(this)
        dialog.setContentView(R.layout.data_entry_layout)
        var btn=dialog.findViewById<Button>(R.id.btn2)
        var edtText1=dialog.findViewById<EditText>(R.id.edtf1)
        var edtText2=dialog.findViewById<EditText>(R.id.edtf2)
        var edtText3=dialog.findViewById<EditText>(R.id.edtf3)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
        btn?.setOnClickListener{
            if (edtText1?.text.isNullOrEmpty()){
                edtText1?.error="Value required"
            }
            else if (edtText2?.text.isNullOrEmpty()){
                edtText2?.error="Value required"
            }
            else if (edtText3?.text.isNullOrEmpty()){
                edtText3?.error="Value required"
            }
            else {
                studentlist.set(position, Student(name = edtText2.text.toString(),
                    rollno = edtText1.text.toString().toInt(),
                    subject = edtText3.text.toString()
                )

                )

                recyclerAdapter.notifyDataSetChanged()

            }

            dialog.dismiss()
            dialog.setCancelable(false)
        }
    }

    override fun add(position: Int) {

        studentlist.add(Student(studentlist[position].rollno?.plus(1)!!,"Manav","Python"))
        recyclerAdapter.notifyDataSetChanged()
    }
}
