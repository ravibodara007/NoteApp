package com.example.noteapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noteapp.Adapter.NotesAdapter
import com.example.noteapp.Database.RoomDB
import com.example.noteapp.Entity.NoteEntity
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.databinding.AddDialogBinding
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var db: RoomDB
    lateinit var adapter: NotesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = RoomDB.init(this)

        initView()
    }

    private fun initView() {
        binding.add.setOnClickListener {

            addNoteDialog()
        }

        adapter = NotesAdapter {
            var isPin = false
            if (it.pin) {
                isPin = false
            } else {
                isPin = true
            }

            var data = NoteEntity(it.title, it.text, it.date, isPin)
            data.id = it.id
            db.note().updateNote(data)
            adapter.update(filternote(db.note().getNotes()))
        }
        adapter.setNotes(db.note().getNotes())
        binding.rcvNotes.layoutManager = GridLayoutManager(this, 2)
        binding.rcvNotes.adapter = adapter
    }

    fun filternote(list: List<NoteEntity>) : ArrayList<NoteEntity>{
        var newlist = ArrayList<NoteEntity>()
        for (l: NoteEntity in list){
            if (!l.pin){
                newlist.add(l)
            }
        }
        for (l: NoteEntity in list){
            if (!l.pin){
                newlist.add(l)
            }
        }

        return newlist
    }
    private fun addNoteDialog() {
        var dialog = Dialog(this)
        var bind = AddDialogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.btnSubmit.setOnClickListener {

            var title = bind.txtTitle.text.toString()
            var text = bind.txtText.text.toString()
            var format = SimpleDateFormat("DD/MMM/YYYY")
            var current = format.format(Date())

            var data = NoteEntity(title, text, current, false)
            db.note().addNote(data)
            adapter.update(db.note().getNotes())
            dialog.dismiss()

        }

        dialog.show()

    }
}