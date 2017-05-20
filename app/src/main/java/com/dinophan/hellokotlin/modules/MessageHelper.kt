package com.dinophan.hellokotlin.modules

import android.content.Context
import android.widget.Toast

/**
 * Created by baophan on 5/20/17.
 */

open class MessageHelper constructor(context: Context) {

    private val context: Context = context

    companion object Factory {
        private var instance: MessageHelper? = null
        fun getInstance(context: Context): MessageHelper {
            if (this.instance == null) {
                this.instance = MessageHelper(context)
            }
            return this.instance!!
        }
    }

    /**
     * Display toast on screen
     * @param message message to show
     */
    open fun showToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

}
