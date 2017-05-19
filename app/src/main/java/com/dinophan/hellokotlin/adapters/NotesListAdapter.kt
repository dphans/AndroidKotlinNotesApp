package com.dinophan.hellokotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.dinophan.hellokotlin.R
import com.dinophan.hellokotlin.models.NoteModel

/**
 * Created by baophan on 5/20/17.
 */

open class NotesListAdapter(notesList: ArrayList<NoteModel>, callback: NotesListAdapter.NotesListAdapterBehavior): RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    private val notesList   = notesList
    private val callback    = callback

    /* declare which view using for display row in list */
    override fun onCreateViewHolder(viewParent: ViewGroup?, viewType: Int): NoteViewHolder {
        val rowView = LayoutInflater.from(viewParent!!.context).inflate(R.layout.list_item_note, viewParent, false)
        return NoteViewHolder(rowView, callback)
    }

    /* declare how many rows will show in list */
    override fun getItemCount(): Int {
        return this.notesList.size
    }

    /* fill data into row (call every time when user scroll to new row) */
    override fun onBindViewHolder(noteViewHolder: NoteViewHolder?, position: Int) {
        noteViewHolder!!.setAttribute(this.notesList[position], position)
    }

    class NoteViewHolder(itemView: View, callback: NotesListAdapterBehavior) : RecyclerView.ViewHolder(itemView) {

        private val noteTitleTextView: TextView? = itemView.findViewById(R.id.list_item_note_title_textview) as TextView?
        private var model: NoteModel    = NoteModel()
        private var pos: Int            = 0

        fun setAttribute(model: NoteModel, position: Int) {
            this.model  = model
            this.pos    = position
            this.noteTitleTextView?.text = model.getBody()
            itemView.setOnClickListener(this.viewOnClick)
        }

        private val viewOnClick = View.OnClickListener {
            callback.onNoteItemClickListener(this.model, this.pos)
        }
    }

    public interface NotesListAdapterBehavior {
        fun onNoteItemClickListener(item: NoteModel, position: Int)
    }

}
