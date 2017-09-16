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



    class LightSwitchPresenter(private val view: View, private val model: Model) {
        interface View {
            fun disable()
            fun enable()

            fun on()
            fun off()
        }

        interface Model {
            fun updateStatus(cb:(Boolean)->Unit)
        }

        init {
            view.disable()

            model.updateStatus {
                view.enable()
                when (it) {
                    true -> view.on()
                    false -> view.off()
                }
            }
        }
    }

    class MockLightSwitchView : LightSwitchPresenter.View {

        var disabled:Boolean = false
        var onStateDisplayed:Boolean = false
        var offStateDisplayed:Boolean = false

        var enabled:Boolean = false

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
    }

    class MockLightSwitchModel : LightSwitchPresenter.Model {
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
    }
}
