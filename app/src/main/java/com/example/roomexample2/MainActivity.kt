package com.example.roomexample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    private lateinit var studentsViewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentsViewModel = ViewModelProvider(this,
            StudentViewModelFactory((application as StudentsApplication).repository)    // we are using the repository using instance of StudentsApplication which has already created
        )[StudentViewModel::class.java]

        // Insert he data into database using insert method of viewModel.
        val students= arrayListOf<Student>()
        students.add(Student(1, "Kanhaiya", 99, 'A'))
        students.add(Student(2, "Vishal", 66, 'C'))
        students.add(Student(3, "Ankit", 50, 'D'))
        for(student in students) {
            studentsViewModel.insert(student)
        }

        val listView: ListView = findViewById(R.id.list)
        adapter=StudentAdapter(this, mutableListOf())
        listView.adapter = adapter // Initially, the adapter will have an empty list

        studentsViewModel.allStudents.observe(this) { students ->
            // Update the cached copy of the students in the adapter.
            students?.let {
                adapter.clear() // Clear previous data in the adapter
                adapter.addAll(it) // Add new list of students to the adapter
                adapter.notifyDataSetChanged() // Notify adapter of the data change
            }
        }

    }
}
