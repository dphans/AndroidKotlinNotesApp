package com.dinophan.hellokotlin.protocols

/**
 * Created by baophan on 5/20/17.
 */

interface OnCreateBehavior {
    fun onLoadComponents()
    fun onLoadControls()
    fun onLoadEvents()
    fun onLoadSettings()
    fun onCustomViews()
}