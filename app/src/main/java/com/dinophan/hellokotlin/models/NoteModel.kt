package com.dinophan.hellokotlin.models

/**
 * Created by baophan on 5/20/17.
 */

open class NoteModel constructor() {

    private var id  : Int       = -1
    private var body: String    = ""

    /**
     * create existing note with id
     */
    constructor(id: Int, body: String): this() {
        this.id     = id
        this.body   = body
    }

    /**
     * parse note from data
     * @param noteData source data for parse note into id and body
     */
    constructor(noteData: String): this() {
        val parseResult: List<String> = noteData.split("~")
        assert(parseResult.isNotEmpty())
        this.id     = Integer.parseInt(parseResult.last())
        this.body   = noteData.substring(0, noteData.length - (parseResult.last().length + 1))
    }

    /**
     * get note id
     * @return null for new note, id for existing note
     */
    open fun getId(): Int? {
        return this.id
    }

    /**
     * set note id
     */
    open fun setId(newId: Int): NoteModel {
        this.id = newId
        return this
    }

    /**
     * get note body
     */
    open fun getBody(): String {
        return this.body
    }

    /**
     * set note body
     */
    open fun setBody(newBody: String?): NoteModel {
        assert(newBody != null && !(newBody as String).isEmpty())
        this.body = newBody!!
        return this
    }

}
