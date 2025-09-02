package com.example.weddingplannerapp

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weddingplannerapp.databinding.ActivityChecklistBinding

class ChecklistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChecklistBinding
    private lateinit var adapter: ChecklistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sample checklist items
        val checklistItems = mutableListOf(
            ChecklistItem("Book Venue"),
            ChecklistItem("Hire Photographer"),
            ChecklistItem("Arrange Catering"),
            ChecklistItem("Plan Mehendi Ceremony"),
            ChecklistItem("Organize Sangeet"),
            ChecklistItem("Book Honeymoon")
        )

        // Setup RecyclerView
        adapter = ChecklistAdapter(checklistItems)
        binding.recyclerChecklist.layoutManager = LinearLayoutManager(this)
        binding.recyclerChecklist.adapter = adapter

        // Add new task using Floating Action Button
        binding.fabAdd.setOnClickListener {
            val editText = EditText(this)
            AlertDialog.Builder(this)
                .setTitle("Add Task")
                .setView(editText)
                .setPositiveButton("Add") { dialog, _ ->
                    val taskTitle = editText.text.toString().trim()
                    if (taskTitle.isNotEmpty()) {
                        adapter.addTask(taskTitle)
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
