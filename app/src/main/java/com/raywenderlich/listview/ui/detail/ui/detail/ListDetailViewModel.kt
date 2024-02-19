package com.raywenderlich.listview.ui.detail.ui.detail

import androidx.lifecycle.ViewModel
import com.raywenderlich.listview.TaskList

class ListDetailViewModel() : ViewModel() {
    lateinit var onTaskAdded: (() -> Unit)
    lateinit var list: TaskList
    fun addTask(task: String) {
        list.tasks.add(task)
        onTaskAdded.invoke()
    }
}