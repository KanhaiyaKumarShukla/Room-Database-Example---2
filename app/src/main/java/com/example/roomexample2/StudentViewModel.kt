package com.example.roomexample2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

// A ViewModel acts as a communication center between the Repository and the UI.
// The ViewModel will transform the data from the Repository, from Flow to LiveData and expose the list of words as LiveData to the UI. This ensures that every time the data changes in the database, your UI is automatically updated.
class StudentViewModel(private val repository: StudentRepository):ViewModel() {

    // using liveData is beneficial because We can put an observer on the data (instead of polling for changes) and only update the UI when the data actually changes.
    val allStudents:LiveData<List<Student>> =repository.allStudents.asLiveData()

    // created a wrapper insert() method that calls the Repository's insert() method. This way, the implementation of insert() is encapsulated from the UI. We're launching a new coroutine and calling the repository's insert, which is a suspend function.
    fun insert(student: Student)=viewModelScope.launch {
        repository.insert(student)
    }
}