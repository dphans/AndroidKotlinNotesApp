package com.dinophan.hellokotlin.services

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.dinophan.hellokotlin.models.NoteModel

/**
 * Created by baophan on 5/20/17.
 */

class NoteService constructor(context: Context) {

    companion object Factory {

        public val PREF_NOTES_STRINGSET: String = "PREF_NOTES_STRINGSET"
        private var instance: NoteService? = null

        fun getInstance(context: Context): NoteService {
            if (instance == null) {
                instance = NoteService(context)
            }
            return instance!!
        }
    }

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    /**
     * list saved notes
     */
    public fun listNotes(): List<NoteModel> {
        val notesSource = this.preferences.getStringSet(PREF_NOTES_STRINGSET, HashSet<String>())
        return notesSource.map { this.fillNotesList(it) }
    }

    /**
     * Save existing note
     * @param noteId null or not existing id will create new
     * @param noteBody string body
     */
    public fun modifyNote(noteId: Int?, noteBody: String) {
        var newNoteId   = noteId
        if (newNoteId == null) {
            newNoteId = this.generateNoteId()
        }
        val listNotes   = ArrayList<NoteModel>(this.listNotes())
        val noteIds     = this.listExistingIds()
        if (noteIds.contains(newNoteId)) {
            (listNotes.find { it.getId() == newNoteId })!!.setBody(noteBody)
        } else {
            listNotes.add(NoteModel(newNoteId, noteBody))
        }

        val notesData   = HashSet<String>()
        listNotes.forEach{ notesData.add(it.getBody() + "~" + it.getId()) }
        this.preferences.edit().putStringSet(PREF_NOTES_STRINGSET, notesData ).apply()
    }

    /**
     * Create new note
     * @param noteBody string body
     */
    public fun addNote(noteBody: String) {
        this.modifyNote(null, noteBody)
    }

    private fun fillNotesList(noteData: String): NoteModel {
        return NoteModel(noteData)
    }

    private fun listExistingIds(): List<Int> {
        return this.listNotes().map { it.getId()!! }
    }

    private fun generateNoteId(): Int {
        val existingIds = this.listExistingIds()
        var newId       = existingIds.size
        while (existingIds.contains(newId)) {
            newId += 1
        }
        return newId
    }

}
