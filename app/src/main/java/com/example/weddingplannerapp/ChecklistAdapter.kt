package com.example.weddingplannerapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChecklistAdapter(private val items: MutableList<ChecklistItem>) :
    RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>() {

    class ChecklistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkboxTask)
        val textTask: TextView = itemView.findViewById(R.id.textTask)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_checklist, parent, false)
        return ChecklistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        val item = items[position]
        holder.textTask.text = item.title
        holder.checkBox.isChecked = item.isCompleted

        // Toggle completed status
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.isCompleted = isChecked
        }

        // Edit task
        holder.btnEdit.setOnClickListener {
            val context = holder.itemView.context
            val editText = EditText(context).apply { setText(item.title) }

            AlertDialog.Builder(context)
                .setTitle("Edit Task")
                .setView(editText)
                .setPositiveButton("Save") { dialog, _ ->
                    val newTitle = editText.text.toString().trim()
                    if (newTitle.isNotEmpty()) {
                        item.title = newTitle
                        notifyItemChanged(position)
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun getItemCount(): Int = items.size

    fun addTask(title: String) {
        items.add(ChecklistItem(title))
        notifyItemInserted(items.size - 1)
    }
}
