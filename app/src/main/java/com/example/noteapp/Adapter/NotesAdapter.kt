package com.example.noteapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteapp.Entity.NoteEntity
import com.example.noteapp.R
import com.example.noteapp.databinding.NoteItemBinding

class NotesAdapter (updatePin: (NoteEntity) -> Unit) : Adapter<NotesAdapter.NotesHolder>() {

    var updatePin = updatePin
    var notes = ArrayList<NoteEntity>()

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
            txtTitleNt.isSelected = true
            notes.get(position).apply {
                txtTitleNt.text = title
                txtTextNt.text = text

                if (pin) {
                    pinIcon.setImageResource(R.drawable.pin)
                } else {
                    pinIcon.setImageResource(R.drawable.unpin)
                }
            }

            pinIcon.setOnClickListener {

                updatePin.invoke(notes.get(position))
            }
        }
    }

    fun update(notes: List<NoteEntity>) {
        this.notes = notes as ArrayList<NoteEntity>
        notifyDataSetChanged()
    }

    fun setNotes(notes: List<NoteEntity>) {

        this.notes = notes as ArrayList<NoteEntity>
    }
}