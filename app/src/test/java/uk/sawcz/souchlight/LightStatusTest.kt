package uk.sawcz.souchlight

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class LightSwitchPresenterTest {
    @Test
    fun lightIsDisabledByDefault() {
        val view = MockLightSwitchView()
        LightSwitchPresenter(view, MockLightSwitchModel())
        assertThat(view.disabled, `is`(true))
    }

    @Test
    fun lightSwitchEnabledWhenModelReportsOnState() {
        val view = MockLightSwitchView()
        val model = MockLightSwitchModel()
        LightSwitchPresenter(view, model)

        model.simulateOnState()

        assertThat(view.enabled, `is`(true))
    }

    @Test
    fun lightSwitchEnabledWhenModelReportsOffState() {
        val view = MockLightSwitchView()
        val model = MockLightSwitchModel()
        LightSwitchPresenter(view, model)

        model.simulateOffState()

        assertThat(view.enabled, `is`(true))
    }

    @Test
    fun lightSwitchSetToOnWhenModelReportsOnState() {
        val view = MockLightSwitchView()
        val model = MockLightSwitchModel()
        LightSwitchPresenter(view, model)

        model.simulateOnState()

        assertThat(view.onStateDisplayed, `is`(true))
    }

    @Test
    fun lightSwitchSetToOffWhenModelReportsOffState() {
        val view = MockLightSwitchView()
        val model = MockLightSwitchModel()
        LightSwitchPresenter(view, model)

        model.simulateOffState()

        assertThat(view.offStateDisplayed, `is`(true))
    }

    @Test
    fun viewCanRequestTheLightToTurnOn() {
        val view = MockLightSwitchView()
        val model = MockLightSwitchModel()
        LightSwitchPresenter(view, model)

        model.simulateOffState()

        view.simulateSwitchOn()

        assertThat(model.onStateRequested, `is`(true))
    }

    @Test
    fun viewCanRequestTheLightToTurnOff() {
        val view = MockLightSwitchView()
        val model = MockLightSwitchModel()
        LightSwitchPresenter(view, model)

        model.simulateOnState()

        view.simulateSwitchOff()

        assertThat(model.offStateRequested, `is`(true))
    }

    class MockLightSwitchView : LightSwitchPresenter.View {

        var disabled:Boolean = false
        var onStateDisplayed:Boolean = false
        var offStateDisplayed:Boolean = false

        var enabled:Boolean = false
        var switchOnCallback: () -> Unit = {}

        var switchOffCallback: () -> Unit = {}

        override fun enable() {
            enabled = true
        }

        override fun disable() {
            disabled = true
        }

        override fun on() {
            onStateDisplayed = true
        }


        override fun off() {
            offStateDisplayed = true
        }

        override fun onSwitchOn(cb:()->Unit) {
            switchOnCallback = cb
        }

        override fun onSwitchOff(cb:()->Unit) {
            switchOffCallback = cb
        }

        fun simulateSwitchOn() {
            switchOnCallback()
        }

        fun simulateSwitchOff() {
            switchOffCallback()
        }
    }

    class MockLightSwitchModel : LightSwitchPresenter.Model {
        var onStateRequested: Boolean = false

        var offStateRequested: Boolean = false
        private var storedUpdateStatusCallback: (Boolean) -> Unit = {}

        override fun updateStatus(cb: (Boolean) -> Unit) {
            storedUpdateStatusCallback = cb
        }

        fun simulateOnState() {
            storedUpdateStatusCallback(true)
        }

        fun simulateOffState() {
            storedUpdateStatusCallback(false)
        }

        override fun turnOn() {
            onStateRequested = true
        }

        override fun turnOff() {
            offStateRequested = true
        }
    }
}
