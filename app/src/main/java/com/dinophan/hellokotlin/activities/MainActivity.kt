package com.dinophan.hellokotlin.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.dinophan.hellokotlin.R
import com.dinophan.hellokotlin.adapters.NotesListAdapter
import com.dinophan.hellokotlin.models.NoteModel
import com.dinophan.hellokotlin.services.NoteService

class MainActivity : BaseActivity(), NotesListAdapter.NotesListAdapterBehavior {

    private var notesList: ArrayList<NoteModel>     = ArrayList<NoteModel>()
    private var notesListAdapter: NotesListAdapter = NotesListAdapter(this.notesList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        this.setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
    }

    override fun onLoadControls() {
        super.onLoadControls()
        val notesRecyclerView           = (this.findViewById(R.id.main_notes_recyclerview) as RecyclerView)
        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        notesRecyclerView.adapter   = this.notesListAdapter
    }

    override fun onLoadSettings() {
        super.onLoadSettings()
        this.reloadNotesList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.main_menu_new_note) {
            NoteService.getInstance(this).addNote("Hello World!")
            this.reloadNotesList()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNoteItemClickListener(item: NoteModel, position: Int) {

    }

    private fun reloadNotesList() {
        this.notesList.clear()
        this.notesList.addAll(NoteService.getInstance(this).listNotes())
        this.notesListAdapter.notifyDataSetChanged()
    }

}
