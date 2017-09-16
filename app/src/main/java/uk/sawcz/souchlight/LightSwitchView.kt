package uk.sawcz.souchlight

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.Switch

/**
 * Copyright © 2017 Media Applications Technologies. All rights reserved.
 */

class LightSwitchView @JvmOverloads constructor(context:Context, attrs: AttributeSet? = null, style:Int = 0) : FrameLayout(context, attrs, style), LightSwitchPresenter.View {

    private val lightSwitch: Switch by lazy {
        findViewById<Switch>(R.id.light_switch)
    }

    private var onSwitchOnCallback: () -> Unit = {}
    private var onSwitchOffCallback: () -> Unit = {}

    override fun onFinishInflate() {
        super.onFinishInflate()
        lightSwitch.setOnCheckedChangeListener { _, state ->
            when(state) {
                true -> onSwitchOnCallback()
                false -> onSwitchOffCallback()
            }
        }
    }

    override fun disable() {
        lightSwitch.isEnabled = false
    }

    override fun enable() {
        lightSwitch.isEnabled = true
    }

    override fun on() {
        lightSwitch.isChecked = true
    }

    override fun off() {
        lightSwitch.isChecked = false
    }

    override fun onSwitchOn(cb: () -> Unit) {
        onSwitchOnCallback = cb
    }


    override fun onSwitchOff(cb: () -> Unit) {
        onSwitchOffCallback = cb
    }

}
