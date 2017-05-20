package com.dinophan.hellokotlin.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.dinophan.hellokotlin.R
import com.dinophan.hellokotlin.models.NoteModel
import com.dinophan.hellokotlin.modules.MessageHelper
import com.dinophan.hellokotlin.services.NoteService

class WriteActivity : BaseActivity() {

    private var noteModel: NoteModel? = null

    public object Intent {
        public val EXTRA_STRING_NOTEDATA: String = "EXTRA_STRING_NOTEDATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        this.setContentView(R.layout.activity_write)
        super.onCreate(savedInstanceState)
        if (this.intent.hasExtra(WriteActivity.Intent.EXTRA_STRING_NOTEDATA)) {
            this.noteModel = NoteModel(this.intent.getStringExtra(WriteActivity.Intent.EXTRA_STRING_NOTEDATA))
            this.setNoteBody(this.noteModel!!.getBody())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menuInflater.inflate(R.menu.menu_write, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.write_menu_save) {
            val noteBody: String? = this.getNoteBody()
            if (noteBody == null) {
                this.finish()
                return false
            }

            if (this.noteModel == null) {
                NoteService.getInstance(this).addNote(noteBody)
                MessageHelper.getInstance(this).showToast(getString(R.string.alert_success_save))
            } else {
                NoteService.getInstance(this).modifyNote(this.noteModel!!.getId(), noteBody)
                MessageHelper.getInstance(this).showToast(getString(R.string.alert_success_update))
            }
            this.finish()
        } else if (item.itemId == R.id.write_menu_cancel) {
            this.finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setNoteBody(noteBody: String) {
        (this.findViewById(R.id.write_note_body_edittext) as EditText).setText(noteBody)
    }

    private fun getNoteBody(): String? {
        val noteBody = (this.findViewById(R.id.write_note_body_edittext) as EditText).text
        if (noteBody == null || noteBody.toString().isEmpty()) return null
        return noteBody.toString()
    }

}
