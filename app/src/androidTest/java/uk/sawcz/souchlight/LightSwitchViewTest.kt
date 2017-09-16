package uk.sawcz.souchlight

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.LayoutInflater
import android.widget.Switch

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*


@RunWith(AndroidJUnit4::class)
class LightSwitchViewTest {
    @Test
    fun lightSwitchViewWillSwitchOn() {
        val lightSwitchView: LightSwitchView = LayoutInflater.from(InstrumentationRegistry.getTargetContext()).inflate(R.layout.cv_lightswitchview, null) as LightSwitchView

        val lightSwitch = lightSwitchView.findViewById<Switch>(R.id.light_switch)

        lightSwitchView.on()

        assertTrue(lightSwitch.isChecked)
    }

    @Test
    fun lightSwitchViewWillSwitchOff() {
        val lightSwitchView: LightSwitchView = LayoutInflater.from(InstrumentationRegistry.getTargetContext()).inflate(R.layout.cv_lightswitchview, null) as LightSwitchView

        val lightSwitch = lightSwitchView.findViewById<Switch>(R.id.light_switch)

        lightSwitchView.off()

        assertFalse(lightSwitch.isChecked)
    }

    @Test
    fun lightSwitchViewEnable() {
        val lightSwitchView: LightSwitchView = LayoutInflater.from(InstrumentationRegistry.getTargetContext()).inflate(R.layout.cv_lightswitchview, null) as LightSwitchView

        val lightSwitch = lightSwitchView.findViewById<Switch>(R.id.light_switch)

        lightSwitchView.enable()

        assertTrue(lightSwitch.isEnabled)
    }

    @Test
    fun lightSwitchViewDisable() {
        val lightSwitchView: LightSwitchView = LayoutInflater.from(InstrumentationRegistry.getTargetContext()).inflate(R.layout.cv_lightswitchview, null) as LightSwitchView

        val lightSwitch = lightSwitchView.findViewById<Switch>(R.id.light_switch)

        lightSwitchView.disable()

        assertFalse(lightSwitch.isEnabled)
    }

    @Test
    fun lightSwitchViewWillCommunicateSwitchingOn() {
        val lightSwitchView: LightSwitchView = LayoutInflater.from(InstrumentationRegistry.getTargetContext()).inflate(R.layout.cv_lightswitchview, null) as LightSwitchView

        val lightSwitch = lightSwitchView.findViewById<Switch>(R.id.light_switch)

        var didSwitch = false
        lightSwitchView.onSwitchOn {
            didSwitch = true
        }

        lightSwitch.performClick()

        assertTrue(didSwitch)
    }
    @Test
    fun lightSwitchViewWillCommunicateSwitchingOff() {
        val lightSwitchView: LightSwitchView = LayoutInflater.from(InstrumentationRegistry.getTargetContext()).inflate(R.layout.cv_lightswitchview, null) as LightSwitchView

        lightSwitchView.on()

        val lightSwitch = lightSwitchView.findViewById<Switch>(R.id.light_switch)

        var didSwitch = false
        lightSwitchView.onSwitchOff {
            didSwitch = true
        }

        lightSwitch.performClick()

        assertTrue(didSwitch)
    }
}
