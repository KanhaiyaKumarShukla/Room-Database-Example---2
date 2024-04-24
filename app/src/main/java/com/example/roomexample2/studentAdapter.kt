package com.example.roomexample2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class StudentAdapter(context: Context, students: List<Student>) : ArrayAdapter<Student>(context, R.layout.list_item, students) {

    private val studentList = mutableListOf<Student>()

    init {
        studentList.addAll(students)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listItemView=convertView
        if(listItemView==null){
            listItemView=LayoutInflater.from(context).inflate(R.layout.list_item, parent,false)
        }
        val currItem=getItem(position)

        val rollNo=listItemView!!.findViewById<TextView>(R.id.text_roll)
        rollNo.text= currItem!!.rollNo.toString()
        val name=listItemView.findViewById<TextView>(R.id.text_name)
        name.text=currItem.name
        val marks=listItemView.findViewById<TextView>(R.id.text_marks)
        marks.text=currItem.marks.toString()
        val grade=listItemView.findViewById<TextView>(R.id.text_grade)
        grade.text=currItem.grade.toString()

        return listItemView
    }
}
