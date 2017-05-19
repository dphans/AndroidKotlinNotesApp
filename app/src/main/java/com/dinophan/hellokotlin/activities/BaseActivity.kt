package com.dinophan.hellokotlin.activities

import com.dinophan.hellokotlin.protocols.OnCreateBehavior

/**
 * Created by baophan on 5/20/17.
 */

open class BaseActivity : android.support.v7.app.AppCompatActivity(), OnCreateBehavior {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        this.onLoadComponents()
        this.onLoadControls()
        this.onLoadEvents()
        this.onLoadSettings()
        this.onCustomViews()
    }

    override fun onLoadComponents() {}
    override fun onLoadControls()   {}
    override fun onLoadEvents()     {}
    override fun onLoadSettings()   {}
    override fun onCustomViews()    {}

}
