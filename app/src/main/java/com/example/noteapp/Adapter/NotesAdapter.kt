package com.example.noteapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteapp.Entity.NoteEntity
import com.example.noteapp.databinding.NoteItemBinding

class NotesAdapter(notes: List<NoteEntity>) : Adapter<NotesAdapter.NotesHolder>() {

    var notes = notes

    class NotesHolder(itemView: NoteItemBinding) : ViewHolder(itemView.root) {

        var binding = itemView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {

        var binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesHolder(binding)
    }

    override fun getItemCount(): Int {

        return notes.size
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {

        holder.binding.apply {
            notes.get(position).apply {
                txtTitleNt.text = title
                txtTextNt.text = text
            }
        }
    }

    fun update(notes: List<NoteEntity>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}